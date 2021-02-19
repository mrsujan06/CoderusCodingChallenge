package com.coderus.codingchallenge.di

import android.app.Application
import android.content.Context
import com.coderus.codingchallenge.utils.ConnectionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
open class AppModule {

    @Provides
    @Singleton
    open fun provideContext(application: Application): Context = application.baseContext

    @Provides
    @Singleton
    fun provideConnectionChecker(context: Context): ConnectionChecker {
        return ConnectionChecker(context)
    }

}