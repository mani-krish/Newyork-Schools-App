package com.assessment.android.presentation.schooldetail

/**
 * User actions for the School Detail screen
 */
sealed interface SchoolDetailAction {
    data object LoadSchoolDetail : SchoolDetailAction
}