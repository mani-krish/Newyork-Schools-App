package com.assessment.android.presentation.schooldetail

import androidx.compose.runtime.Immutable
import com.assessment.android.domain.model.SchoolDetail

/**
 * UI state for the School Detail screen
 */
@Immutable
data class SchoolDetailUiState(
    val isLoading: Boolean = false,
    val schoolDetail: SchoolDetail? = null,
    val errorMessage: String? = null
)
