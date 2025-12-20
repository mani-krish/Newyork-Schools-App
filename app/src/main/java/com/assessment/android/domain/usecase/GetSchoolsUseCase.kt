package com.assessment.android.domain.usecase

import com.assessment.android.domain.model.School
import com.assessment.android.domain.repository.SchoolsRepository
import com.assessment.android.domain.toDomain
import com.assessment.core.network.utils.ResponseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSchoolsUseCase @Inject constructor(private val schoolsRepository: SchoolsRepository) {
    suspend operator fun invoke(): Flow<ResponseWrapper<List<School>>> {
        return schoolsRepository.getSchools()
            .map { response ->
                when (response) {
                    is ResponseWrapper.Success -> {
                        val schools = response.data
                            .asSequence()
                            .mapNotNull { it.toDomain() }
                            .sortedBy { it.schoolName.lowercase() }
                            .toList()
                        ResponseWrapper.Success(schools)
                    }

                    is ResponseWrapper.HttpError -> response
                    is ResponseWrapper.GenericError -> response
                }
            }
    }
}