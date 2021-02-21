package com.coderus.codingchallenge.repository

import com.coderus.codingchallenge.database.RocketEntities
import com.coderus.codingchallenge.network.domain.RocketLaunchJson
import kotlinx.coroutines.flow.Flow

interface RocketLaunchRepository {
    suspend fun fetchRocketLaunchesFromNetwork(): List<RocketLaunchJson>
    suspend fun refreshRocketLaunchDb()
    fun fetchRocketLaunchList(): Flow<List<RocketEntities>>
}