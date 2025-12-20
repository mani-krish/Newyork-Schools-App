package com.assessment.core.network.utils

/**
 * A generic class that holds a loading the result of an async operation
 */
sealed class NetworkResult<out T>() {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class HttpError(val code: Int?, val httpErrorMessage: String, val errorBody: String?) :
        NetworkResult<Nothing>()

    data object GenericError : NetworkResult<Nothing>()
}