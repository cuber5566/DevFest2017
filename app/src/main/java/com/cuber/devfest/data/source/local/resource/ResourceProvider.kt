package com.cuber.devfest.data.source.local.resource

import android.app.Application
import android.content.res.Resources
import android.support.annotation.StringRes
import android.support.annotation.VisibleForTesting

class ResourceProvider private constructor() {

    private lateinit var resource: Resources

    fun init(application: Application) {
        resource = application.resources
    }

    fun getResource(): Resources = resource

    fun getString(@StringRes stringId: Int): String = resource.getString(stringId)


    companion object {

        private var INSTANCE: ResourceProvider? = null

        @JvmStatic
        fun getInstance(): ResourceProvider {
            return INSTANCE ?: ResourceProvider()
                    .apply { INSTANCE = this }
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}