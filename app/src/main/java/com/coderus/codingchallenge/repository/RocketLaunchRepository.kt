package com.coderus.codingchallenge.repository

import androidx.lifecycle.LiveData
import com.coderus.codingchallenge.database.RocketEntities
import com.coderus.codingchallenge.database.RocketLaunchDatabase
import com.coderus.codingchallenge.network.api.APIService
import com.coderus.codingchallenge.network.domain.RocketLaunchJson
import com.coderus.codingchallenge.network.domain.asDatabaseModel
import com.coderus.codingchallenge.utils.NetworkBoundResource
import com.coderus.codingchallenge.utils.Resource
import com.example.kotlinpractice.roomtodolist.api.ApiResponse
import com.example.kotlinpractice.roomtodolist.utils.AppExecutors

/**
 * Repository implementation for fetching Rocket Launches
 * from Network and Storing them to the launchDatabase
 * */
class RocketLaunchRepository(
    private val appExecutors: AppExecutors,
    private val apiService: APIService,
    private val db: RocketLaunchDatabase
) {

    private fun getRocketLaunchesFromNet(): LiveData<ApiResponse<List<RocketLaunchJson>>> {
        return apiService.getRocketLaunchList()
    }

    fun loadRocketLaunches(): LiveData<Resource<List<RocketEntities>>> {
        return object : NetworkBoundResource<List<RocketEntities>, List<RocketLaunchJson>>() {

            override suspend fun saveCallResults(items: List<RocketLaunchJson>) {
                db.rocketLaunchDao.insertRocketList(items.asDatabaseModel())
            }

            override fun shouldFetch(data: List<RocketEntities>?): Boolean {
                return true
            }

            override suspend fun loadFromDb(): List<RocketEntities> {
                return db.rocketLaunchDao.getRocketLauncher()
            }

            override fun createCall(): LiveData<ApiResponse<List<RocketLaunchJson>>> {
                return getRocketLaunchesFromNet()
            }


        }.asLiveData()

//        return object :
//            NetworkBoundResource<List<RocketEntities>, List<RocketLaunchJson>>(appExecutors) {
//            override fun saveCallResult(item: List<RocketLaunchJson>) {
//                db.rocketLaunchDao.insertRocketList(item.asDatabaseModel())
//            }
//
//            override fun shouldFetch(data: List<RocketEntities>?): Boolean {
//                return true
//            }
//
//            override fun loadFromDb(): LiveData<List<RocketEntities>> {
//                return db.rocketLaunchDao.getRocketLauncher()
//            }
//
//            override fun createCall(): LiveData<ApiResponse<List<RocketLaunchJson>>> {
//                return getRocketLaunchesFromNet()
//            }
//
//        }.asLiveData()
    }

}

