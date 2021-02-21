package com.coderus.codingchallenge.di

import com.coderus.codingchallenge.network.api.APIService
import com.coderus.codingchallenge.database.RocketLaunchDatabase
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import com.coderus.codingchallenge.repository.RocketLaunchRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
open class RepositoryModule {

    @Provides
    open fun provideRocketLaunchesRepository(
        apiService: APIService,
        launchDatabase: RocketLaunchDatabase
    ): RocketLaunchRepository = RocketLaunchRepositoryImp(apiService, launchDatabase)
}