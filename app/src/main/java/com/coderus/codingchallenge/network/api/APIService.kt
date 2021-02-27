package com.coderus.codingchallenge.network.api

import com.coderus.codingchallenge.network.domain.RocketLaunchJson
import com.coderus.codingchallenge.utils.Constant.Companion.LAUNCHES
import com.coderus.codingchallenge.utils.Resource
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit API to retrieve data from the SpaceX API.
 */
interface APIService {

    /**
     * Retrieve list of rocket launches from the SpaceX API.
     */
    @GET(LAUNCHES)
    suspend fun getRocketLaunchList(): Response<List<RocketLaunchJson>>
}