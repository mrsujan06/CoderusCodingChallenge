package com.coderus.codingchallenge.api

import com.coderus.codingchallenge.model.RocketLaunch
import com.coderus.codingchallenge.utils.Constant.Companion.BASE_URL
import com.coderus.codingchallenge.utils.Constant.Companion.LAUNCHES
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.Executors

/**
 * Retrofit API to retrieve data from the SpaceX API.
 */
interface APIService {

    /**
     * Retrieve list of rocket launches from the SpaceX API.
     */
    @GET(LAUNCHES)
    suspend fun getRocketLaunchList(): List<RocketLaunch>

}
