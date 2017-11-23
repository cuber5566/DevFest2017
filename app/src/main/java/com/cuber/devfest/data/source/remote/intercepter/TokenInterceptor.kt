package com.cuber.devfest.data.source.remote.intercepter

import com.cuber.devfest.data.source.local.preference.PreferencesKey
import com.cuber.devfest.data.source.local.preference.PreferencesProvider
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class TokenInterceptor(
        private var preferencesProvider: PreferencesProvider = PreferencesProvider.getInstance()
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        return chain.proceed(originalRequest.newBuilder()
                .header(AUTHORIZATION, TOKEN_PREFIX + getToken())
                .build())

    }

    private fun getToken():String = preferencesProvider[PreferencesKey.TOKEN_AUTH, String::class.java]

    companion object {
        private val AUTHORIZATION = "Authorization"
        private val TOKEN_PREFIX = "DevFest "
    }
}