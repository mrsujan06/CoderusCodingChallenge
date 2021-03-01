package com.coderus.codingchallenge.utils

sealed class Resource<out T> {

    data class Success<out T>(val value: T) : Resource<T>()

    data class Error<out T>(val message: String, val value: T) : Resource<T>()

    data class Loading<out T>(val value: T? = null) : Resource<T>()

    companion object {

        fun <T> loading(value: T?): Resource<T> = Loading(value)

        fun <T> success(value: T): Resource<T> = Success(value)

        fun <T> error(error_msg: String, value: T): Resource<T> = Error(error_msg, value)

    }

}