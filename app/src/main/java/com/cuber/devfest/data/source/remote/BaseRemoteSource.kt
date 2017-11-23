package com.cuber.devfest.data.source.remote

import com.cuber.devfest.data.source.remote.exception.ApiErrorCode
import com.cuber.devfest.data.source.remote.exception.ApiException
import com.cuber.devfest.data.source.remote.response.BaseResponse

open class BaseRemoteSource {

    fun <T : BaseResponse> isApiSuccess(response: T): T {

        if (response.status == ApiErrorCode.SUCCESS) {
            return response
        } else {
            throw ApiException(response)
        }
    }
}