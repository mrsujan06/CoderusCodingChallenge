package com.coderus.codingchallenge.repository

import androidx.lifecycle.LiveData
import com.coderus.codingchallenge.domain.RocketLaunch
import com.coderus.codingchallenge.network.domain.RocketLaunchJson

interface RocketLaunchRepository {
    fun fetchRocketLaunchList(): LiveData<List<RocketLaunch>>
    suspend fun fetchRocketLaunchesFromNetwork(): List<RocketLaunchJson>
    suspend fun refreshRocketLaunchDb()
}