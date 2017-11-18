package com.cuber.devfest

import android.app.Application
import com.cuber.devfest.util.PreferencesTool
import com.cuber.devfest.util.ResourceTool

/**
 * Created by cuber on 2017/11/18.
 */
class DevFestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ResourceTool.getInstance().init(this)
        PreferencesTool.getInstance().init(this)
    }
}