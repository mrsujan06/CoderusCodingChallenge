package com.coderus.codingchallenge.network.dto

import com.coderus.codingchallenge.database.RocketEntities
import com.coderus.codingchallenge.domain.RocketLaunch
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Model object to store data about a rocket launch.
 */
@JsonClass(generateAdapter = true)
data class RocketLaunchNetwork(
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

fun List<RocketLaunchNetwork>.asDomainModel(): List<RocketLaunch> {
    return map {
        RocketLaunch(
            flightNumber = it.flightNumber,
            name = it.name,
            dateUTC = it.dateUTC,
            details = it.details,
            success = it.success,
            upcoming = it.upcoming
        )
    }
}

fun List<RocketLaunchNetwork>.asDatabaseModel(): List<RocketEntities> {
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
