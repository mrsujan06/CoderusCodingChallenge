package com.coderus.codingchallenge.network.api

import androidx.lifecycle.LiveData
import com.coderus.codingchallenge.network.domain.RocketLaunchJson
import com.coderus.codingchallenge.utils.Constant.Companion.LAUNCHES
import com.example.kotlinpractice.roomtodolist.api.ApiResponse
import okhttp3.internal.concurrent.Task
import retrofit2.http.GET

/**
 * Retrofit API to retrieve data from the SpaceX API.
 */
interface APIService {

    /**
     * Retrieve list of rocket launches from the SpaceX API.
     */
    @GET(LAUNCHES)
    fun getRocketLaunchList(): LiveData<ApiResponse<List<RocketLaunchJson>>>
}