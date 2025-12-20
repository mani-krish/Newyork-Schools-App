package com.assessment.android.presentation.schoollist

/**
 * User actions for the School List screen
 */
sealed interface SchoolListAction {
    data object LoadSchools : SchoolListAction
}