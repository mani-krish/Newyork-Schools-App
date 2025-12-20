package com.assessment.android.presentation.schooldetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assessment.android.R
import com.assessment.android.domain.model.SchoolDetail
import com.assessment.android.presentation.component.AppToolbar
import com.assessment.android.presentation.component.EmptyState
import com.assessment.android.presentation.component.ErrorState
import com.assessment.android.presentation.component.LoadingState

/**
 * School detail screen that observes UI state and renders loading, error, or content
 */
@Composable
fun SchoolDetailScreen(
    viewModel: SchoolDetailViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppToolbar(
                title = stringResource(R.string.school_detail_screen_title),
                showBack = true,
                onBackClick = onNavigateBack
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when {
                uiState.isLoading -> LoadingState()
                uiState.errorMessage != null -> ErrorState(stringResource(R.string.error_message))
                uiState.schoolDetail != null -> {
                    uiState.schoolDetail?.let {
                        SchoolDetailContent(school = it)
                    }
                }

                else -> EmptyState(stringResource(R.string.empty_state_message))
            }
        }
    }
}

/**
 * Displays the main content of the school detail screen
 */
@Composable
fun SchoolDetailContent(
    school: SchoolDetail
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        SummarySection(school)
        ScoresSection(school)
    }
}

/**
 * Shows SAT score cards for reading, writing, and math
 */
@Composable
fun ScoresSection(schoolDetail: SchoolDetail) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ScoreItem(
            stringResource(R.string.sat_reading),
            schoolDetail.satReadingAvgScore, Icons.Outlined.MenuBook, modifier = Modifier.weight(1f)
        )
        ScoreItem(
            stringResource(R.string.sat_writing),
            schoolDetail.satWritingAvgScore, Icons.Outlined.Edit, modifier = Modifier.weight(1f)
        )
        ScoreItem(
            stringResource(R.string.sat_math),
            schoolDetail.satMathAvgScore, Icons.Outlined.Calculate, modifier = Modifier.weight(1f)
        )
    }
}

/**
 * Displays an individual SAT score with label and icon
 */
@Composable
private fun ScoreItem(
    label: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(icon, contentDescription = null)
            Text(label, style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center)
            Text(value, style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
        }
    }
}

/**
 * Displays additional school summary such as average score and test takers
 */
@Composable
fun SummarySection(schoolDetail: SchoolDetail) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.Calculate, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = stringResource(
                        R.string.sat_average_scores,
                        schoolDetail.satAverageScore
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.People, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = stringResource(
                        R.string.test_takers_with_value,
                        schoolDetail.numOfSatTestTakers
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}