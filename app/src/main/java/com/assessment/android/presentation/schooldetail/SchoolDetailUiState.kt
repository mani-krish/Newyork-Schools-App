package com.assessment.android.presentation.schooldetail

import com.assessment.android.domain.model.SchoolDetail

/**
 * UI state for the School Detail screen
 */
data class SchoolDetailUiState(
    val isLoading: Boolean = false,
    val schoolDetail: SchoolDetail? = null,
    val errorMessage: String? = null
)
