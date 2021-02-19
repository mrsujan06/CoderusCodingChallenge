package com.coderus.codingchallenge.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RocketEntities::class], version = 1, exportSchema = false)
abstract class RocketDatabase : RoomDatabase() {
    abstract val rocketDao: RocketDao
}