package com.cuber.devfest.data.source.remote.exception

import com.cuber.devfest.data.source.remote.response.BaseResponse


class ApiException(
        response: BaseResponse
) : RuntimeException(response.toString()) {

    var statusCode: String = response.status
    var errorMessage: String = response.message
}
