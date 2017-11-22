package com.cuber.devfest

import android.app.Application
import com.cuber.devfest.data.source.local.dao.DaoProvider
import com.cuber.devfest.data.source.local.preference.PreferencesProvider
import com.cuber.devfest.data.source.local.resource.ResourceProvider

/**
 * Created by cuber on 2017/11/18.
 */
class DevFestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DaoProvider.getInstance().init(this)
        ResourceProvider.getInstance().init(this)
        PreferencesProvider.getInstance().init(this)
    }
}