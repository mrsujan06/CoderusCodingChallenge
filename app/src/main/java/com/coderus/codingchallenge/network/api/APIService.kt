package com.coderus.codingchallenge.api

import com.coderus.codingchallenge.network.dto.RocketLaunchNetwork
import com.coderus.codingchallenge.utils.Constant.Companion.LAUNCHES
import retrofit2.http.GET

/**
 * Retrofit API to retrieve data from the SpaceX API.
 */
interface APIService {

    /**
     * Retrieve list of rocket launches from the SpaceX API.
     */
    @GET(LAUNCHES)
    suspend fun getRocketLaunchList(): List<RocketLaunchNetwork>

}
