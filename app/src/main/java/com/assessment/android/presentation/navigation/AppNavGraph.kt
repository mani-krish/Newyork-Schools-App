package com.assessment.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assessment.android.presentation.schooldetail.SchoolDetailScreen
import com.assessment.android.presentation.schoollist.SchoolListScreen

/**
 * Defines the app’s navigation graph and screen destinations
 */
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: SchoolListRoute = SchoolListRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<SchoolListRoute> {
            SchoolListScreen(
                onSchoolClick = { dbn ->
                    navController.navigate(SchoolDetailRoute(dbn))
                }
            )
        }

        composable<SchoolDetailRoute> {
            SchoolDetailScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}