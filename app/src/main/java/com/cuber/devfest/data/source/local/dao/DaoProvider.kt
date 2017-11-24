package com.cuber.devfest.data.source.local.dao

import android.app.Application
import android.arch.persistence.room.Room
import android.support.annotation.VisibleForTesting

class DaoProvider(

        private var room: AppDatabase

) {
    var productDao: ProductDao = room.productDao()

    companion object {

        private var INSTANCE: DaoProvider? = null
        private var DATABASE_NAME = "DevFest2017"
        private var ROOM: AppDatabase? = null

        @JvmStatic
        fun init(application: Application) {
            ROOM = Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME).build()
        }

        @JvmStatic
        fun getInstance(): DaoProvider {
            return INSTANCE ?: DaoProvider(

                    room = ROOM!!

            ).apply { INSTANCE = this }
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
