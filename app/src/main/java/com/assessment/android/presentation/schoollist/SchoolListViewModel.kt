package com.assessment.android.presentation.schoollist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assessment.android.domain.usecase.GetSchoolsUseCase
import com.assessment.core.network.utils.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the School List screen handling MVI actions and UI state updates
 */
@HiltViewModel
class SchoolListViewModel @Inject constructor(
    private val getSchoolsUseCase: GetSchoolsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SchoolListUiState())
    val uiState: StateFlow<SchoolListUiState> = _uiState.asStateFlow()

    init {
        onAction(SchoolListAction.LoadSchools)
    }

    /*Handles MVI actions from the UI layer*/
    fun onAction(action: SchoolListAction) {
        when (action) {
            SchoolListAction.LoadSchools -> loadSchools()
        }
    }

    /*Fetches schools and updates UI state based on result*/
    private fun loadSchools() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            getSchoolsUseCase()
                .collect { result ->
                    when (result) {
                        is ResponseWrapper.Success -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    schools = result.data,
                                    errorMessage = null
                                )
                            }
                        }

                        is ResponseWrapper.HttpError -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = result.httpErrorMessage
                                )
                            }
                        }

                        is ResponseWrapper.GenericError -> {
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