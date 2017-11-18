package com.cuber.devfest.util

import android.app.Application
import android.content.res.Resources
import android.support.annotation.StringRes

class ResourceTool {

    fun init(application: Application) {
        resource = application.resources
    }

    fun getResource(): Resources = resource!!

    fun getString(@StringRes stringId: Int): String = resource!!.getString(stringId)


    companion object {

        private var INSTANCE: ResourceTool? = null
        private var resource: Resources? = null

        @JvmStatic
        fun getInstance(): ResourceTool {
            return INSTANCE ?: ResourceTool()
                    .apply { INSTANCE = this }
        }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}