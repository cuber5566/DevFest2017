package com.cuber.devfest

import android.app.Application
import com.cuber.devfest.data.source.local.dao.DaoProvider
import com.cuber.devfest.data.source.local.preference.PreferencesProvider
import com.cuber.devfest.data.source.local.resource.ResourceProvider

class DevFestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DaoProvider.init(this)
        ResourceProvider.init(this)
        PreferencesProvider.init(this)
    }
}