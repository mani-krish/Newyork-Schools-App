package com.assessment.android.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * Displays a centered empty state message.
 */
@Composable
fun EmptyState(message: String) {
    Text(
        message,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.error
    )
}