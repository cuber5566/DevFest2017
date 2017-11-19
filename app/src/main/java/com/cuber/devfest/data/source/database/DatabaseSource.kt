package com.cuber.devfest.data.source.database

import android.app.Application
import android.arch.persistence.room.Room

class DatabaseSource {

    lateinit var db: AppDatabase

    fun init(application: Application) {
        db = Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME).build()
    }

    companion object {

        private var INSTANCE: DatabaseSource? = null
        private var DATABASE_NAME = "DevFest2017"

        @JvmStatic
        fun getInstance(): DatabaseSource {
            return INSTANCE ?: DatabaseSource()
                    .apply { INSTANCE = this }
        }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}