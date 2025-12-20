package com.assessment.android.data.source

import com.assessment.android.data.api.SchoolsApi
import com.assessment.core.network.api.RestApiCall
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SchoolsNetworkDataSource @Inject constructor(private val schoolsApi: SchoolsApi) {
    fun fetchSchools() = flow {
        emit(RestApiCall.safeApiCall { schoolsApi.getSchools() })
    }

    fun fetchSchoolDetail(dbn: String) = flow {
        emit(RestApiCall.safeApiCall { schoolsApi.getSchoolDetail(dbn) })
    }
}