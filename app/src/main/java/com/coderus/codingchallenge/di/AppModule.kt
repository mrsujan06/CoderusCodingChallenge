package com.coderus.codingchallenge.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
open class AppModule {

    @Provides
    open fun provideContext(application: Application): Context = application.baseContext
}