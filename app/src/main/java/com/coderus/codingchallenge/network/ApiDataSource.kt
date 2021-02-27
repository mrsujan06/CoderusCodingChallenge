package com.coderus.codingchallenge.network

import com.coderus.codingchallenge.network.api.APIService
import com.coderus.codingchallenge.network.base.BaseDataSource
import javax.inject.Inject

open class RocketApiDataSource @Inject constructor(private val apiService: APIService) :
    BaseDataSource() {

    suspend fun getData() = getData { apiService.getRocketLaunchList() }

}