package com.coderus.codingchallenge.di

import android.content.Context
import androidx.room.Room
import com.coderus.codingchallenge.database.RocketLaunchDao
import com.coderus.codingchallenge.database.RocketLaunchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
open class DatabaseModule {

    @Provides
    open fun provideRocketDatabase(context: Context): RocketLaunchDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            RocketLaunchDatabase::class.java,
            "Rockets"
        ).fallbackToDestructiveMigration().build()

    @Provides
    open fun provideRocketDao(rocketLaunchDatabase: RocketLaunchDatabase): RocketLaunchDao =
        rocketLaunchDatabase.rocketLaunchDao
}