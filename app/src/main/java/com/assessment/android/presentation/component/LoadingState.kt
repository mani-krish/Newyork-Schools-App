package com.assessment.android.presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Displays a centered loading indicator
 */
@Composable
fun LoadingState() {
    CircularProgressIndicator(
        modifier = Modifier
            .size(40.dp),
        color = MaterialTheme.colorScheme.primary,
        strokeWidth = 3.dp
    )
}
