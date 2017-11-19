package com.cuber.devfest.data.source.resource

import android.app.Application
import android.content.res.Resources
import android.support.annotation.StringRes

class ResourceSource {

    fun init(application: Application) {
        resource = application.resources
    }

    fun getResource(): Resources = resource!!

    fun getString(@StringRes stringId: Int): String = resource!!.getString(stringId)


    companion object {

        private var INSTANCE: ResourceSource? = null
        private var resource: Resources? = null

        @JvmStatic
        fun getInstance(): ResourceSource {
            return INSTANCE ?: ResourceSource()
                    .apply { INSTANCE = this }
        }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}