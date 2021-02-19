package com.coderus.codingchallenge.repository

import androidx.lifecycle.LiveData
import com.coderus.codingchallenge.domain.RocketLaunch

interface RocketLaunchRepository {
    fun fetchRocketLaunches(): LiveData<List<RocketLaunch>>
    suspend fun refreshRocketLaunches()
}