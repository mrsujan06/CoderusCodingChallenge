package com.coderus.codingchallenge.repository

import com.coderus.codingchallenge.database.RocketLaunchDatabase
import com.coderus.codingchallenge.network.RocketApiDataSource
import com.coderus.codingchallenge.network.domain.asDatabaseModel
import com.coderus.codingchallenge.utils.responseLiveData

/**
 * Repository implementation for fetching Rocket Launches
 * from Network and Storing them to the launchDatabase
 * */
open class RocketLaunchRepository(
    private val apiService: RocketApiDataSource,
    private val rocketDatabase: RocketLaunchDatabase
) {

    val rocketLaunch = responseLiveData(
        roomQueryToRetrieveData = { rocketDatabase.rocketLaunchDao.getRocketLauncher() },
        networkRequest = { apiService.getData() },
        roomQueryToSaveData = { rocketDatabase.rocketLaunchDao.insertRocketList(it.asDatabaseModel()) }
    )
}