package com.assessment.android.domain

import com.assessment.android.data.model.SchoolDetailDto
import com.assessment.android.data.model.SchoolDto
import com.assessment.android.domain.model.School
import com.assessment.android.domain.model.SchoolDetail

fun SchoolDto.toDomain(): School? {
    val dbnValue = dbn?.trim()
    val schoolName = schoolName?.trim()

    if (dbnValue.isNullOrEmpty() || schoolName.isNullOrEmpty()) return null

    return School(
        dbn = dbn,
        schoolName = schoolName,
        phoneNumber = phoneNumber.orEmpty(),
        email = email.orEmpty(),
        website = website.orEmpty(),
        totalStudents = totalStudents.orEmpty()
    )
}

fun SchoolDetailDto.toDomain(): SchoolDetail {
    return SchoolDetail(
        numOfSatTestTakers = numOfSatTestTakers.orEmpty(),
        satReadingAvgScore = satCriticalReadingAvgScore.orEmpty(),
        satMathAvgScore = satMathAvgScore.orEmpty(),
        satWritingAvgScore = satWritingAvgScore.orEmpty()
    )
}