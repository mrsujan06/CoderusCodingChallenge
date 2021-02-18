package com.coderus.codingchallenge.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
open class AppModule {

    @Provides
    @PerApplication
    open fun provideContext(application: Application): Context = application.baseContext

}