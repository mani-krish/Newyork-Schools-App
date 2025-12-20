package com.assessment.android.data.repository

import com.assessment.android.data.model.SchoolDetailDto
import com.assessment.android.data.model.SchoolDto
import com.assessment.android.data.source.SchoolsNetworkDataSource
import com.assessment.android.domain.repository.SchoolsRepository
import com.assessment.core.network.utils.ResponseWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(private val schoolsRemoteDataSource: SchoolsNetworkDataSource) :
    SchoolsRepository {

    override suspend fun getSchools(): Flow<ResponseWrapper<List<SchoolDto>>> {
        return schoolsRemoteDataSource.fetchSchools()
    }

    override suspend fun getSchoolDetail(dbn: String): Flow<ResponseWrapper<List<SchoolDetailDto>>> {
        return schoolsRemoteDataSource.fetchSchoolDetail(dbn)
    }
}
