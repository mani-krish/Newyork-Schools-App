package com.assessment.android.presentation.school

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assessment.android.domain.usecase.GetSchoolUseCase
import com.assessment.core.network.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the School Detail screen handling MVI actions and UI state updates
 */
@HiltViewModel
class SchoolDetailViewModel @Inject constructor(
    private val getSchoolUseCase: GetSchoolUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val schoolDbn: String = savedStateHandle["schoolDbn"] ?: ""

    private val _uiState = MutableStateFlow(SchoolDetailUiState())
    val uiState: StateFlow<SchoolDetailUiState> = _uiState.asStateFlow()

    init {
        onAction(SchoolDetailAction.LoadSchoolDetail)
    }

    /*Handles MVI actions from the UI layer*/
    fun onAction(action: SchoolDetailAction) {
        when (action) {
            SchoolDetailAction.LoadSchoolDetail -> loadSchoolDetail()
        }
    }

    /*Fetches school detail and updates UI state based on result*/
    private fun loadSchoolDetail() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            getSchoolUseCase(schoolDbn)
                .collectLatest { result ->
                    when (result) {
                        is NetworkResult.Success -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    schoolDetail = result.data,
                                    errorMessage = null
                                )
                            }
                        }

                        is NetworkResult.HttpError -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = result.message
                                )
                            }
                        }

                        is NetworkResult.GenericError -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = result.toString()
                                )
                            }
                        }
                    }
                }
        }
    }
}