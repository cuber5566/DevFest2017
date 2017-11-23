package com.cuber.devfest.data.source.remote.service

import com.cuber.devfest.data.source.remote.ApiConfig
import com.cuber.devfest.data.source.remote.response.ProductListResponse
import com.cuber.devfest.data.source.remote.response.ProductResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET(ApiConfig.BASE_URL + "product/productId/{product_id}")
    fun getProductById(@Path("product_id") productId: String): Single<ProductResponse>

    @GET(ApiConfig.BASE_URL + "product")
    fun getProductList(): Single<ProductListResponse>
}