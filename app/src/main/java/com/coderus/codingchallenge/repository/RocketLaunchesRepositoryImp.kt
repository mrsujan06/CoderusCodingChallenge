package com.coderus.codingchallenge.repository

import com.coderus.codingchallenge.api.APIService
import com.coderus.codingchallenge.model.RocketLaunch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RocketLaunchesRepositoryImp(private val apiService: APIService) : RocketLaunchesRepository {

    /**
     * Fetch data from API
     * **/
    @ExperimentalCoroutinesApi
    override fun fetchRocketLaunches(): Flow<List<RocketLaunch>> = flow {
        val repo = apiService.getRocketLaunchList()
        emit(repo)
    }.flowOn(Dispatchers.IO)
}