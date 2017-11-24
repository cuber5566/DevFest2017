package com.cuber.devfest.data.source.remote.service

import android.support.annotation.VisibleForTesting
import com.cuber.devfest.data.source.api.ApiConfig
import com.cuber.devfest.data.source.remote.intercepter.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceProvider {

    val productService: ProductService

    init {

        val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(ApiConfig.TIME_OUT_CONNECT.toLong(), TimeUnit.SECONDS)
                .readTimeout(ApiConfig.TIME_OUT_READ.toLong(), TimeUnit.SECONDS)
                .writeTimeout(ApiConfig.TIME_OUT_WRITE.toLong(), TimeUnit.SECONDS)
                .addInterceptor(TokenInterceptor())
                .build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConfig.BASE_URL)
                .client(client)
                .build()

        productService = retrofit.create(ProductService::class.java)
    }

    companion object {

        private var INSTANCE: ServiceProvider? = null

        @JvmStatic
        fun getInstance(): ServiceProvider {
            return INSTANCE ?: ServiceProvider()
                    .apply { INSTANCE = this }
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
