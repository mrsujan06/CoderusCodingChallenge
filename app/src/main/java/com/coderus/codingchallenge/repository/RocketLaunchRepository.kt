package com.coderus.codingchallenge.repository

import com.coderus.codingchallenge.database.RocketEntities
import com.coderus.codingchallenge.network.domain.RocketLaunchJson

interface RocketLaunchRepository {
    suspend fun fetchRocketLaunchesFromNetwork(): List<RocketLaunchJson>
    suspend fun refreshRocketLaunchDb()
    suspend fun fetchRocketLaunchList(): List<RocketEntities>
}