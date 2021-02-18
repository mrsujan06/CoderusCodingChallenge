package com.coderus.codingchallenge.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Model object to store data about a rocket launch.
 */
@JsonClass(generateAdapter = true)
data class RocketLaunch(
    @Json(name = "flight_number")
    val flightNumber: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "date_utc")
    val dateUTC: String,
    @Json(name = "details")
    val details: String?,
    @Json(name = "success")
    val success: Boolean?,
    @Json(name = "upcoming")
    val upcoming: Boolean?
)
