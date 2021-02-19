package com.coderus.codingchallenge.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.coderus.codingchallenge.api.APIService
import com.coderus.codingchallenge.database.RocketDatabase
import com.coderus.codingchallenge.database.asDomainModel
import com.coderus.codingchallenge.domain.RocketLaunch
import com.coderus.codingchallenge.network.dto.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

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
    override fun fetchRocketLaunches(): LiveData<List<RocketLaunch>> {
        return Transformations.map(database.rocketDao.getRocketLauncher()) {
            it.asDomainModel()
        }
    }

    /**
     * Fetch data from API and insert it to the Room database
     * **/
    override suspend fun refreshRocketLaunches() {
        withContext(Dispatchers.IO) {
            Timber.d("Fetch Rocket Launches called")
            val rocketLaunchNetwork = apiService.getRocketLaunchList()
            database.rocketDao.insertRocketList(rocketLaunchNetwork.asDatabaseModel())
        }
    }

}