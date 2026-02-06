package com.assessment.core.network.api

import com.assessment.core.network.utils.NetworkResult
import retrofit2.Response
import java.io.IOException

/**
 * Executes Rest API calls and maps results to NetworkResult.
 * Ensures consistent success, HTTP error, and network failure handling
 */
object NetworkResponseHandler {

    suspend fun <T : Any> execute(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val response = apiCall.invoke()

            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResult.Success(data = it)
                } ?: NetworkResult.GenericError
            } else {
                // HTTP error response 4xx / 5xx
                try {
                    response.errorBody()?.string()
                } catch (exception: Exception) {
                    null
                }
                NetworkResult.HttpError(
                    message = response.message().takeIf { it.isNotBlank() }
                        ?: "HTTP error ${response.code()}")
            }
        } catch (exception: IOException) {
            // Network failure timeout, no internet
            NetworkResult.GenericError
        } catch (exception: Exception) {
            // Any other unexpected errors
            NetworkResult.GenericError
        }
    }
}