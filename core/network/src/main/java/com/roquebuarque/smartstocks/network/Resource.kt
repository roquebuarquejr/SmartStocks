package com.roquebuarque.smartstocks.network

data class Resource<out T>(val status: Status, val data: T?, val throwable: Throwable?) {

    enum class Status {
        SUCCESS,
        LOADING,
        ERROR
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }


        fun <T> error(throwable: Throwable, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, throwable)
        }

    }
}