package com.coderus.codingchallenge.di

import android.content.Context
import androidx.room.Room
import com.coderus.codingchallenge.database.RocketDao
import com.coderus.codingchallenge.database.RocketDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class DatabaseModule {

    @Provides
    @Singleton
    open fun provideRocketDatabase(context: Context): RocketDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            RocketDatabase::class.java,
            "Rockets"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    open fun provideRocketDao(rocketDatabase: RocketDatabase): RocketDao =
        rocketDatabase.rocketDao
}