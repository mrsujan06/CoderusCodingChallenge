package com.coderus.codingchallenge.di

import com.coderus.codingchallenge.api.APIService
import com.coderus.codingchallenge.database.RocketDatabase
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import com.coderus.codingchallenge.repository.RocketLaunchRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
open class RepositoryModule {
    @Provides
    @Singleton
    open fun provideRocketLaunchesRepository(
        apiService: APIService,
        database: RocketDatabase
    ): RocketLaunchRepository =
        RocketLaunchRepositoryImp(apiService, database)
}