package com.cuber.devfest.data.source.local.preference

import java.lang.reflect.Type

interface AppPreference {

    fun <T : Any> get(key: PreferencesKey, clazz: Class<T>): T

    fun <T> get(key: PreferencesKey, type: Type): T

    fun clear()
}