package com.cuber.devfest.data.source.local.preference

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.support.annotation.VisibleForTesting
import com.google.gson.Gson
import java.lang.reflect.Type

class PreferencesProvider(

        private var preferences: SharedPreferences

) : AppPreference {

    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    override fun <T : Any> get(key: PreferencesKey, clazz: Class<T>): T {
        return when (clazz) {
            String::class.java -> clazz.cast(preferences.getString(key.name, ""))
            Boolean::class.java -> clazz.cast(preferences.getBoolean(key.name, false))
            Int::class.java -> clazz.cast(preferences.getInt(key.name, 0))
            Float::class.java -> clazz.cast(preferences.getFloat(key.name, 0.0f))
            Long::class.java -> clazz.cast(preferences.getLong(key.name, 0))
            else -> Gson().fromJson(preferences.getString(key.name, ""), clazz)
        } as T
    }

    override fun <T> get(key: PreferencesKey, type: Type): T =
            Gson().fromJson(preferences.getString(key.name, ""), type)

    override fun put(key: PreferencesKey, `object`: Any) {
        when {
            `object`.javaClass == String::class.java -> preferences.edit().putString(key.name,
                    `object` as String).apply()
            `object`.javaClass == Boolean::class.java -> preferences.edit().putBoolean(key.name, `object` as Boolean).apply()
            `object`.javaClass == Int::class.java -> preferences.edit().putInt(key.name, `object` as Int).apply()
            `object`.javaClass == Float::class.java -> preferences.edit().putFloat(key.name, `object` as Float).apply()
            `object`.javaClass == Long::class.java -> preferences.edit().putLong(key.name, `object` as Long).apply()
            else -> preferences.edit().putString(key.name, Gson().toJson(`object`)).apply()
        }
    }

    override fun clear() {
        preferences.edit().clear().apply()
    }

    companion object {

        private val NAME = "DevFest2017"
        private var INSTANCE: PreferencesProvider? = null
        private var PREFERENCES: SharedPreferences? = null

        @JvmStatic
        fun init(application: Application) {
            PREFERENCES = application.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        }

        @JvmStatic
        fun getInstance(): PreferencesProvider {
            return INSTANCE ?: PreferencesProvider(

                    preferences = PREFERENCES!!

            ).apply { INSTANCE = this }
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}