package com.coderus.codingchallenge.network.base

import com.coderus.codingchallenge.utils.Resource
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getData(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return formateError(" ${response.code()} ${response.message()}")
        } catch (exception: Exception) {
            return exception.message?.let { formateError(it) }!!
        }
    }

    private fun <T> formateError(errorMessage: String): Resource<T> {
        return Resource.failure("Network call has failed for a following reason: $errorMessage")
    }

}