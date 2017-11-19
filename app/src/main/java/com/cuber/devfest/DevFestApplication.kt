package com.cuber.devfest

import android.app.Application
import com.cuber.devfest.data.source.database.DatabaseSource
import com.cuber.devfest.data.source.preference.PreferencesSource
import com.cuber.devfest.data.source.resource.ResourceSource

class DevFestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DatabaseSource.getInstance().init(this)
        ResourceSource.getInstance().init(this)
        PreferencesSource.getInstance().init(this)
    }
}