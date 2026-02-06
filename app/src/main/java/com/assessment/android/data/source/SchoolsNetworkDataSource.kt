package com.assessment.android.data.source

import com.assessment.android.data.api.SchoolsApi
import com.assessment.core.network.api.NetworkResponseHandler
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SchoolsNetworkDataSource @Inject constructor(private val schoolsApi: SchoolsApi) {
    fun fetchSchools() = flow {
        emit(NetworkResponseHandler.execute { schoolsApi.getSchools() })
    }

    fun fetchSchool(dbn: String) = flow {
        emit(NetworkResponseHandler.execute { schoolsApi.getSchool(dbn) })
    }
}