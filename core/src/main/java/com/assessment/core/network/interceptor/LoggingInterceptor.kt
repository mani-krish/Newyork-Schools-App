package com.assessment.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        return chain.proceed(request)
    }
}