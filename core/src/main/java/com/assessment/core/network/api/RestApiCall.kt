package com.assessment.core.network.api

import com.assessment.core.network.utils.ResponseWrapper
import retrofit2.Response
import java.io.IOException

/**
 * Executes Rest API calls and maps results to ResponseWrapper.
 * Ensures consistent success, HTTP error, and network failure handling
 */
object RestApiCall {

    suspend fun <T : Any> safeApiCall(apiCall: suspend () -> Response<T>): ResponseWrapper<T> {
        return try {
            val response = apiCall.invoke()

            if (response.isSuccessful) {
                response.body()?.let {
                    ResponseWrapper.Success(data = it)
                } ?: ResponseWrapper.GenericError
            } else {
                // HTTP error response 4xx / 5xx
                val errorBody = try {
                    response.errorBody()?.string()
                } catch (exception: Exception) {
                    null
                }
                ResponseWrapper.HttpError(
                    code = response.code(),
                    httpErrorMessage = response.message().takeIf { it.isNotBlank() }
                        ?: "HTTP error ${response.code()}",
                    errorBody = errorBody)
            }
        } catch (exception: IOException) {
            // Network failure timeout, no internet
            ResponseWrapper.GenericError
        } catch (exception: Exception) {
            // Any other unexpected errors
            ResponseWrapper.GenericError
        }
    }
}