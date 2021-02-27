package com.coderus.codingchallenge.di

import com.coderus.codingchallenge.database.RocketLaunchDatabase
import com.coderus.codingchallenge.network.RocketApiDataSource
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
open class RepositoryModule {

    @Provides
    open fun provideRocketLaunchesRepository(
        apiService: RocketApiDataSource,
        launchDatabase: RocketLaunchDatabase
    ): RocketLaunchRepository =
        RocketLaunchRepository(apiService, launchDatabase)
}