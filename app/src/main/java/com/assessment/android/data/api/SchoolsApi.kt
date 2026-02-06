package com.assessment.android.data.api

import com.assessment.android.data.model.SchoolDetailDto
import com.assessment.android.data.model.SchoolDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolsApi {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools(): Response<List<SchoolDto>>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSchool(@Query(value = "dbn") dbn: String): Response<List<SchoolDetailDto>>
}