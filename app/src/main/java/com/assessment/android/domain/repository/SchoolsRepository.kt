package com.assessment.android.domain.repository

import com.assessment.android.data.model.SchoolDetailDto
import com.assessment.android.data.model.SchoolDto
import com.assessment.core.network.utils.ResponseWrapper
import kotlinx.coroutines.flow.Flow

/**
 * Interface to the data layer
 */
interface SchoolsRepository {
    suspend fun getSchools(): Flow<ResponseWrapper<List<SchoolDto>>>
    suspend fun getSchoolDetail(dbn: String): Flow<ResponseWrapper<List<SchoolDetailDto>>>
}