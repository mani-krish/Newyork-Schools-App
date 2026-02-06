package com.assessment.android.presentation.home

/**
 * User actions for the School List screen
 */
sealed interface HomeAction {
    data object LoadSchools : HomeAction
}