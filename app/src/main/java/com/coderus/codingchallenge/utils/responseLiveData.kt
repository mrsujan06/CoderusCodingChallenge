package com.coderus.codingchallenge.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T, L> responseLiveData(
    roomQueryToRetrieveData: () -> LiveData<T>,
    networkRequest: suspend () -> Resource<L>,
    roomQueryToSaveData: suspend (L) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val source = roomQueryToRetrieveData().map { Resource.success(it) }
        emitSource(source)

        when (val responseStatus = networkRequest()) {
            is Resource.Success -> {
                roomQueryToSaveData(responseStatus.value)
            }

            is Resource.Failure -> {
                emit(Resource.failure(responseStatus.message))
                emitSource(source)
            }

            else -> {
                emit(Resource.failure("Something went wrong, please try again later"))
                emitSource(source)
            }
        }

    }