package com.coderus.codingchallenge.network.domain

import com.coderus.codingchallenge.database.RocketEntities
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Model object to store data about a rocket launch Json.
 */
@JsonClass(generateAdapter = true)
data class RocketLaunchJson(
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

/**
 * Extension function to map [RocketLaunchJson] to [RocketEntities]
 */
fun List<RocketLaunchJson>.asDatabaseModel(): List<RocketEntities> {
    return map {
        RocketEntities(
            flightNumber = it.flightNumber,
            name = it.name,
            dateUTC = it.dateUTC,
            details = it.details,
            success = it.success,
            upcoming = it.upcoming
        )
    }
}
