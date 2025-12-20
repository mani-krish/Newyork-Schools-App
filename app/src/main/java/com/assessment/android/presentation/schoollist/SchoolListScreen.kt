package com.assessment.android.presentation.schoollist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
    viewModel: SchoolListViewModel = hiltViewModel(),
    onSchoolClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val title = stringResource(R.string.schools_screen_title)

    Scaffold(
        topBar = { AppToolbar(title = title) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                uiState.isLoading -> LoadingState()
                uiState.errorMessage != null -> ErrorState(stringResource(R.string.error_message))
                uiState.schools.isNotEmpty() -> SchoolList(
                    schools = uiState.schools,
                    onSchoolClick = onSchoolClick
                )

                else -> EmptyState(stringResource(R.string.empty_state_message))
            }
        }
    }
}

/**
 * Displays a scrollable list of schools.
 */
@Composable
private fun SchoolList(
    schools: List<School>,
    onSchoolClick: (String) -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(
            items = schools,
            key = { it.dbn }
        ) { school ->
            SchoolItem(
                school = school,
                onClick = { onSchoolClick(school.dbn) }
            )
        }
    }
}

/**
 * Displays an individual school item with basic information.
 */
@Composable
private fun SchoolItem(
    school: School,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .semantics { contentDescription = school.schoolName },
        elevation = CardDefaults.cardElevation(2.dp),
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
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}