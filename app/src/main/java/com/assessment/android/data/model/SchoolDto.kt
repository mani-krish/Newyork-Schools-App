package com.assessment.android.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SchoolDto(
    @SerialName("dbn")
    val dbn: String? = null,
    @SerialName("school_name")
    val schoolName: String? = null,
    @SerialName("phone_number")
    val phoneNumber: String? = null,
    @SerialName("school_email")
    val email: String? = null,
    @SerialName("website")
    val website: String? = null,
    @SerialName("total_students")
    val totalStudents: String? = null
)