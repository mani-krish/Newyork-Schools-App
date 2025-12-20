package com.assessment.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.assessment.android.presentation.navigation.AppNavGraph
import com.assessment.android.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main entry point for the app hosting Compose UI, navigation, and splash handling
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition {
            viewModel.isLoading.value
        }

        setContent {
            AppTheme {
                AppNavGraph()
            }
        }
    }
}