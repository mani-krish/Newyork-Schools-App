package com.assessment.android.presentation.home

import androidx.compose.runtime.Immutable
import com.assessment.android.domain.model.School

/**
 * UI state for the School List screen
 */
@Immutable
data class HomeUiState(
    val isLoading: Boolean = false,
    val schools: List<School> = emptyList(),
    val errorMessage: String? = null
)
