package com.assessment.android.presentation.school

/**
 * User actions for the School Detail screen
 */
sealed interface SchoolDetailAction {
    data object LoadSchoolDetail : SchoolDetailAction
}