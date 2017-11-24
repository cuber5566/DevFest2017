package com.cuber.devfest.data.source.local.resource

import android.app.Application
import android.content.res.Resources
import android.support.annotation.VisibleForTesting

class ResourceProvider private constructor() : AppResource {

    private lateinit var resource: Resources

    fun init(application: Application) {
        resource = application.resources
    }

    override fun resource(): Resources = resource

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