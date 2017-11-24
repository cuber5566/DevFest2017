package com.cuber.devfest.data.source.local.resource

import android.app.Application
import android.content.res.Resources
import android.support.annotation.VisibleForTesting

class ResourceProvider(

        private var resources: Resources

) : AppResource {

    override fun resource(): Resources = resources

    companion object {

        private var INSTANCE: ResourceProvider? = null
        private var RESOURCE: Resources? = null

        @JvmStatic
        fun init(application: Application) {
            RESOURCE = application.resources
        }

        @JvmStatic
        fun getInstance(): ResourceProvider {
            return INSTANCE ?: ResourceProvider(

                    resources = RESOURCE!!

            ).apply { INSTANCE = this }
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}