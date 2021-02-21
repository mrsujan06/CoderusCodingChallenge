package com.coderus.codingchallenge.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRocketList(rocketList: List<RocketEntities>)

    @Query("SELECT * FROM ${RocketEntities.ROCKET_TABLE}")
    fun getRocketLauncher(): Flow<List<RocketEntities>>

}