package com.assessment.android.domain.usecase

import com.assessment.android.domain.model.SchoolDetail
import com.assessment.android.domain.repository.SchoolsRepository
import com.assessment.android.domain.toDomain
import com.assessment.core.network.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSchoolUseCase @Inject constructor(private val schoolsRepository: SchoolsRepository) {
    suspend operator fun invoke(dbn: String): Flow<NetworkResult<SchoolDetail>> {
        return schoolsRepository.getSchool(dbn)
            .map { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        val model = response.data.firstOrNull()
                        if (model != null) {
                            NetworkResult.Success(model.toDomain())
                        } else {
                            NetworkResult.GenericError
                        }
                    }
                    is NetworkResult.HttpError -> response
                    is NetworkResult.GenericError -> response
                }
            }
    }
}