package com.cuber.devfest.data.source.api

import com.cuber.devfest.data.source.api.intercepter.TokenInterceptor
import com.cuber.devfest.data.source.api.service.ProductService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiSource {

    val productService: ProductService

    init {

        val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
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
