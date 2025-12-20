package com.assessment.core.network.utils

/**
 * A generic class that holds a loading the result of an async operation
 */
sealed class ResponseWrapper<out T>() {
    data class Success<out T>(val data: T) : ResponseWrapper<T>()
    data class HttpError(val code: Int?, val httpErrorMessage: String, val errorBody: String?) :
        ResponseWrapper<Nothing>()

    data object GenericError : ResponseWrapper<Nothing>()
}