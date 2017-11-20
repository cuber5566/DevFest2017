package com.cuber.devfest.data.source.api.intercepter

import com.cuber.devfest.data.source.preference.PreferencesKey
import com.cuber.devfest.data.source.preference.PreferencesSource
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class TokenInterceptor(
        private var preferencesSource: PreferencesSource = PreferencesSource.getInstance()
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        return chain.proceed(originalRequest.newBuilder()
                .header(AUTHORIZATION, TOKEN_PREFIX + getToken())
                .build())

    }

    private fun getToken():String = preferencesSource[PreferencesKey.TOKEN, String::class.java]

    companion object {
        private val AUTHORIZATION = "Authorization"
        private val TOKEN_PREFIX = "DevFest "
    }
}