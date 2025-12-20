package com.assessment.android.presentation.schoollist

import com.assessment.android.domain.model.School

/**
 * UI state for the School List screen
 */
data class SchoolListUiState(
    val isLoading: Boolean = false,
    val schools: List<School> = emptyList(),
    val errorMessage: String? = null
)
