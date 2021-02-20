package com.coderus.codingchallenge.di

import com.coderus.codingchallenge.network.api.APIService
import com.coderus.codingchallenge.database.RocketDatabase
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import com.coderus.codingchallenge.repository.RocketLaunchRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class RepositoryModule {
    @Provides
    @Singleton
    open fun provideRocketLaunchesRepository(
        apiService: APIService,
        database: RocketDatabase
    ): RocketLaunchRepository = RocketLaunchRepositoryImp(apiService, database)
}