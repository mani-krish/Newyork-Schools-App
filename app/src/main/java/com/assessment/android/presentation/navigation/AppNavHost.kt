package com.assessment.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assessment.android.presentation.home.SchoolListScreen
import com.assessment.android.presentation.school.SchoolDetailScreen

/**
 * Defines the app’s navigation graph and screen destinations
 */
@Composable
fun AppNavHost(
    modifier: Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SchoolListRoute

    ) {
        composable<SchoolListRoute> {
            SchoolListScreen(
                homeViewModel = hiltViewModel(),
                onSchoolClick = { dbn ->
                    navController.navigate(SchoolDetailRoute(dbn))
                }
            )
        }

        composable<SchoolDetailRoute> {
            SchoolDetailScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}