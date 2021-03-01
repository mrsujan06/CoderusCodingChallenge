package com.coderus.codingchallenge.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RocketLaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRocketList(rocketList: List<RocketEntities>)

    @Query("SELECT * FROM ${RocketEntities.ROCKET_TABLE}")
    fun getRocketLauncher(): LiveData<List<RocketEntities>>

}