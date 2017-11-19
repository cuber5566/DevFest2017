package com.cuber.devfest.data.source.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiSource private constructor() {

    init {

        val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(NetworkConfig.TIME_OUT_CONNECT.toLong(), TimeUnit.SECONDS)
                .readTimeout(NetworkConfig.TIME_OUT_READ.toLong(), TimeUnit.SECONDS)
                .writeTimeout(NetworkConfig.TIME_OUT_WRITE.toLong(), TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkConfig.BASE_URL)
                .client(client)
                .build()
    }

    companion object {

        private var INSTANCE: ApiSource? = null

        @JvmStatic
        fun getInstance(): ApiSource {
            return INSTANCE ?: ApiSource()
                    .apply { INSTANCE = this }
        }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
