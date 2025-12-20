package com.assessment.android.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class School(
    val dbn: String,
    val schoolName: String,
    val phoneNumber: String,
    val email: String,
    val website: String,
    val totalStudents: String
)