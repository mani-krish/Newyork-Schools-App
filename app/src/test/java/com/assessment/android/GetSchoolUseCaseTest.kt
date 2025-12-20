package com.assessment.android

import com.assessment.android.data.model.SchoolDto
import com.assessment.android.domain.model.School
import com.assessment.android.domain.repository.SchoolsRepository
import com.assessment.android.domain.usecase.GetSchoolsUseCase
import com.assessment.core.network.utils.ResponseWrapper
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.Before

class GetSchoolsUseCaseTest {

    private lateinit var repository: SchoolsRepository
    private lateinit var useCase: GetSchoolsUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetSchoolsUseCase(repository)
    }

    @Test
    fun returnSchoolsAfterSuccess() = runTest {
        // Given
        val schoolDtoList = listOf(
            SchoolDto(
                schoolName = "Clinton School Writers & Artists, M.S. 260",
                dbn = "06M540",
                phoneNumber = "212-690-6800",
                email = "dfannin@schools.nyc.gov",
                website = "www.aprandolph.com",
                totalStudents = "1438"
            )
        )

        // Mock the repository to return a Success wrapper with the DTO list
        coEvery { repository.getSchools() } returns flowOf(ResponseWrapper.Success(schoolDtoList))

        // Expected domain model list after mapping
        val schools = listOf(
            School(
                schoolName = "Clinton School Writers & Artists, M.S. 260",
                dbn = "06M540",
                phoneNumber = "212-690-6800",
                email = "dfannin@schools.nyc.gov",
                website = "www.aprandolph.com",
                totalStudents = "1438"
            )
        )

        // When
        val result = useCase.invoke().first()

        // Then
        assertTrue(result is ResponseWrapper.Success)
        val success = result as ResponseWrapper.Success
        assertEquals(
            schools, success.data
        )
    }

    @Test
    fun `invoke should return Http Error response`() = runTest {
        // Given
        val httpError =
            ResponseWrapper.HttpError(code = 404, httpErrorMessage = "Not Found", errorBody = null)

        // Mock the repository to return the HttpError
        coEvery { repository.getSchools() } returns flowOf(httpError)

        // When
        val result = useCase.invoke().first()

        // Then
        assertTrue(result is ResponseWrapper.HttpError)
        assertEquals(httpError, result)
    }

    @Test
    fun `invoke returns Success with empty list when repository returns empty`() = runTest {
        // Mock the repository to return a Success wrapper with an empty list
        coEvery { repository.getSchools() } returns flowOf(ResponseWrapper.Success(emptyList()))

        // Define the expected empty list of domain models
        val expectedSchools = emptyList<School>()

        // When
        val result = useCase.invoke().first()

        // Then
        assertTrue(result is ResponseWrapper.Success)

        val success = result as ResponseWrapper.Success
        assertEquals(expectedSchools, success.data)
    }
}