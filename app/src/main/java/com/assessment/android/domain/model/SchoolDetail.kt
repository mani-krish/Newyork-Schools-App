package com.assessment.android.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class SchoolDetail(
    val numOfSatTestTakers: String,
    val satReadingAvgScore: String,
    val satMathAvgScore: String,
    val satWritingAvgScore: String
) {
    val satAverageScore: String
        get() {
            val r = satReadingAvgScore.toIntOrNull()
            val m = satMathAvgScore.toIntOrNull()
            val w = satWritingAvgScore.toIntOrNull()
            return if (r != null && m != null && w != null) {
                ((r + m + w) / 3).toString()
            } else ""
        }
}