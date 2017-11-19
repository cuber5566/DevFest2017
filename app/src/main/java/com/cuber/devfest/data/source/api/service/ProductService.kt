package com.cuber.devfest.data.source.api.service

import com.cuber.devfest.data.source.api.ApiConfig
import com.cuber.devfest.data.source.api.response.ProductResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET(ApiConfig.BASE_URL + "product/productId/{product_id}")
    fun getProductById(@Path("product_id") productId: String): Single<ProductResponse>

}