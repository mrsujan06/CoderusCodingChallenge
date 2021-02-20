package com.coderus.codingchallenge.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.coderus.codingchallenge.network.api.APIService
import com.coderus.codingchallenge.database.RocketDatabase
import com.coderus.codingchallenge.database.asDomainModel
import com.coderus.codingchallenge.domain.RocketLaunch
import com.coderus.codingchallenge.network.domain.RocketLaunchJson
import com.coderus.codingchallenge.network.domain.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository implementation for fetching Rocket Launches
 * from Network and Storing them to the database
 * */
class RocketLaunchRepositoryImp(
    private val apiService: APIService,
    private val database: RocketDatabase
) : RocketLaunchRepository {

    /**
     * Gets the data from database and map it to domain model
     * */
    override fun fetchRocketLaunchList(): LiveData<List<RocketLaunch>> =
        Transformations.map(database.rocketDao.getRocketLauncher()) {
            it.asDomainModel()
        }

    /**
     * Fetch data from API
     * **/
    override suspend fun fetchRocketLaunchesFromNetwork(): List<RocketLaunchJson> =
        apiService.getRocketLaunchList()

    /**
     * Insert Network result to the Room database
     * **/
    override suspend fun refreshRocketLaunchDb() {
        withContext(Dispatchers.IO) {
            val rocketLaunchNetwork = fetchRocketLaunchesFromNetwork()
            database.rocketDao.insertRocketList(rocketLaunchNetwork.asDatabaseModel())
        }
    }
}