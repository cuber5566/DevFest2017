package com.cuber.devfest.data.source.remote.response

import com.cuber.devfest.data.model.Product
import com.google.gson.annotations.SerializedName

data class ProductListResponse(

        @SerializedName("product_list")
        var productList: List<Product>

) : BaseResponse()