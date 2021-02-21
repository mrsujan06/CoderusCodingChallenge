package com.coderus.codingchallenge.utils

class Resource<T> constructor(
    val status: Status,
    var data: T? = null,
    val message: String? = null,
) {

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(status = Status.SUCCESS, data = data)
        }

        fun <T> error(msg: String?, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(status = Status.LOADING, data = data)
        }

        fun <T> cached(data: T?): Resource<T> {
            return Resource(status = Status.CACHED, data = data)
        }
    }

}

enum class Status {
    SUCCESS, ERROR, LOADING, CACHED
}
