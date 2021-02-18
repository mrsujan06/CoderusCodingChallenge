package com.coderus.codingchallenge.repository

import com.coderus.codingchallenge.model.RocketLaunch
import kotlinx.coroutines.flow.Flow

interface RocketLaunchesRepository {
    fun fetchRocketLaunches(): Flow<List<RocketLaunch>>
}