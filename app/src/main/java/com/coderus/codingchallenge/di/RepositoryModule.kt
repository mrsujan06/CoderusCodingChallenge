package com.coderus.codingchallenge.di

import com.coderus.codingchallenge.database.RocketLaunchDatabase
import com.coderus.codingchallenge.network.api.APIService
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import com.example.kotlinpractice.roomtodolist.utils.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
open class RepositoryModule {

    @Provides
    open fun provideRocketLaunchesRepository(
        appExecutors: AppExecutors,
        apiService: APIService,
        db: RocketLaunchDatabase
    ): RocketLaunchRepository =
        RocketLaunchRepository(appExecutors, apiService, db)
}