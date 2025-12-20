package com.assessment.android.presentation.navigation

import kotlinx.serialization.Serializable

/**
 * Navigation route definitions for the school list and detail screen
 */
@Serializable
data object SchoolListRoute

@Serializable
data class SchoolDetailRoute(val schoolDbn: String)