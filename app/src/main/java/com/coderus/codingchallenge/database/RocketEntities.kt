package com.coderus.codingchallenge.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coderus.codingchallenge.database.RocketEntities.Companion.ROCKET_TABLE
import com.coderus.codingchallenge.domain.RocketLaunch
import com.squareup.moshi.Json

@Entity(tableName = ROCKET_TABLE)
data class RocketEntities constructor(
    @PrimaryKey
    @ColumnInfo(name = FLIGHT_NUMBER)
    val flightNumber: Int,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = DATE_UTC)
    val dateUTC: String,
    @ColumnInfo(name = DETAILS)
    val details: String?,
    @ColumnInfo(name = SUCCESS)
    val success: Boolean?,
    @ColumnInfo(name = UPCOMING)
    val upcoming: Boolean?
) {
    companion object {

        //Table
        const val ROCKET_TABLE = "rocket_table"

        //Column
        const val FLIGHT_NUMBER = "flight_number"
        const val NAME = "name"
        const val DATE_UTC = "date_utc"
        const val DETAILS = "details"
        const val SUCCESS = "success"
        const val UPCOMING = "upcoming"
    }
}

fun List<RocketEntities>.asDomainModel(): List<RocketLaunch> {
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