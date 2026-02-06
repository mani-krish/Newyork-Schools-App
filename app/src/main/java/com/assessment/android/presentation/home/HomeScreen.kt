package com.assessment.android.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Public
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assessment.android.R
import com.assessment.android.domain.model.School
import com.assessment.android.presentation.component.AppToolbar
import com.assessment.android.presentation.component.EmptyState
import com.assessment.android.presentation.component.ErrorState
import com.assessment.android.presentation.component.LoadingState

/**
 * School list screen that observes UI state and renders loading, error, or content.
 */
@Composable
fun SchoolListScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onSchoolClick: (String) -> Unit = {}
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val title = stringResource(R.string.schools_screen_title)

    Scaffold(
        topBar = { AppToolbar(modifier = modifier, title = title) },
        content = { innerPadding ->
            SchoolListContent(
                modifier = modifier.padding(innerPadding),
                isLoading = uiState.isLoading,
                error = uiState.errorMessage,
                schools = uiState.schools,
                onSchoolClick = {
                    onSchoolClick.invoke(it)
                }
            )
        }
    )
}

/**
 * Displays a scrollable list of schools.
 */
@Composable
private fun SchoolListContent(
    modifier: Modifier,
    isLoading: Boolean,
    error: String?,
    schools: List<School>,
    onSchoolClick: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading) {
            LoadingState()
        } else if (error != null) {
            ErrorState(stringResource(R.string.error_message))
        } else if (schools.isEmpty()) {
            EmptyState(stringResource(R.string.empty_state_message))
        } else {
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState,
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(top = 12.dp, bottom = 12.dp)
            ) {
                items(
                    items = schools,
                    key = { it.dbn }
                ) { school ->
                    SchoolItem(
                        school = school,
                        onClick = { onSchoolClick(school.dbn) },
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                    )
                }
            }
        }
    }
}

/**
 * Displays an individual school item with basic information.
 */
@Composable
private fun SchoolItem(
    modifier: Modifier = Modifier,
    school: School,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = school.schoolName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            InfoRow(
                icon = Icons.Outlined.People,
                value = stringResource(R.string.students_label, school.totalStudents)
            )
            InfoRow(Icons.Outlined.Public, school.website)
            InfoRow(Icons.Outlined.Email, school.email)
            InfoRow(Icons.Outlined.Phone, school.phoneNumber)
        }
    }
}

/**
 * Displays a single row of labeled information with an icon.
 */
@Composable
private fun InfoRow(
    icon: ImageVector,
    value: String
) {
    if (value.isBlank()) return

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}