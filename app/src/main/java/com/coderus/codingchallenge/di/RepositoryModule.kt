package com.coderus.codingchallenge.di

import com.coderus.codingchallenge.api.APIService
import com.coderus.codingchallenge.repository.RocketLaunchesRepository
import com.coderus.codingchallenge.repository.RocketLaunchesRepositoryImp
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
    open fun provideRocketLaunchesRepository(apiService: APIService): RocketLaunchesRepository =
        RocketLaunchesRepositoryImp(apiService)
}