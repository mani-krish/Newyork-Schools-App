package com.assessment.android.di

import com.assessment.android.data.api.SchoolsApi
import com.assessment.android.data.repository.SchoolRepositoryImpl
import com.assessment.android.data.source.SchoolsNetworkDataSource
import com.assessment.android.domain.repository.SchoolsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun bindSchoolApi(retrofit: Retrofit): SchoolsApi =
        retrofit.create(SchoolsApi::class.java)

    @Provides
    @Singleton
    fun bindSchoolRepository(remoteDataSource: SchoolsNetworkDataSource): SchoolsRepository =
        SchoolRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun bindSchoolDataSource(schoolService: SchoolsApi): SchoolsNetworkDataSource =
        SchoolsNetworkDataSource(schoolService)
}