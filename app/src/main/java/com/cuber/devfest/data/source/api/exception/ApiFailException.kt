package com.cuber.devfest.data.source.api.exception

import retrofit2.Response


class ApiFailException(
        response: Response<*>
) : RuntimeException(response.toString()) {

    var statusCode: Int = response.code()
    var errorMessage: String = response.errorBody()?.string() ?: ""

}
