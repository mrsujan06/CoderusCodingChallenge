package com.coderus.codingchallenge.repository

import com.coderus.codingchallenge.database.RocketLaunchDatabase
import com.coderus.codingchallenge.database.RocketEntities
import com.coderus.codingchallenge.network.api.APIService
import com.coderus.codingchallenge.network.domain.RocketLaunchJson
import com.coderus.codingchallenge.network.domain.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * Repository implementation for fetching Rocket Launches
 * from Network and Storing them to the launchDatabase
 * */
class RocketLaunchRepositoryImp(
    private val apiService: APIService,
    private val launchDatabase: RocketLaunchDatabase
) : RocketLaunchRepository {

    /**
     * Gets the data from launchDatabase
     * */
    override fun fetchRocketLaunchList(): Flow<List<RocketEntities>> {
        return launchDatabase.rocketLaunchDao.getRocketLauncher()
    }

    /**
     * Fetch data from API
     * **/
    override suspend fun fetchRocketLaunchesFromNetwork(): List<RocketLaunchJson> =
        apiService.getRocketLaunchList()

    /**
     * Insert Network result to the Room launchDatabase
     * **/
    override suspend fun refreshRocketLaunchDb() {
        withContext(Dispatchers.IO) {
            val rocketLaunchNetwork = fetchRocketLaunchesFromNetwork()
            launchDatabase.rocketLaunchDao.insertRocketList(rocketLaunchNetwork.asDatabaseModel())
        }
    }
}