package com.assessment.android.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SchoolDetailDto(
    @SerialName("num_of_sat_test_takers") val numOfSatTestTakers: String? = null,
    @SerialName("sat_critical_reading_avg_score") val satCriticalReadingAvgScore: String? = null,
    @SerialName("sat_math_avg_score") val satMathAvgScore: String? = null,
    @SerialName("sat_writing_avg_score") val satWritingAvgScore: String? = null
)
