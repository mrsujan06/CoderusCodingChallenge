package com.coderus.codingchallenge.domain

data class RocketLaunch(
    val flightNumber: Int,
    val name: String,
    val dateUTC: String,
    val details: String?,
    val success: Boolean?,
    val upcoming: Boolean?
)