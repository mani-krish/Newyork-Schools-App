package com.assessment.android.domain.usecase

import com.assessment.android.domain.model.SchoolDetail
import com.assessment.android.domain.repository.SchoolsRepository
import com.assessment.android.domain.toDomain
import com.assessment.core.network.utils.ResponseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSchoolDetailUseCase @Inject constructor(private val schoolsRepository: SchoolsRepository) {
    suspend operator fun invoke(dbn: String): Flow<ResponseWrapper<SchoolDetail>> {
        return schoolsRepository.getSchoolDetail(dbn)
            .map { response ->
                when (response) {
                    is ResponseWrapper.Success -> {
                        val model = response.data.firstOrNull()
                        if (model != null) {
                            ResponseWrapper.Success(model.toDomain())
                        } else {
                            ResponseWrapper.GenericError
                        }
                    }
                    is ResponseWrapper.HttpError -> response
                    is ResponseWrapper.GenericError -> response
                }
            }
    }
}