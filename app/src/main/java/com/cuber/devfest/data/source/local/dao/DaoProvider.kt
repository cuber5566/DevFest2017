package com.cuber.devfest.data.source.local.dao

import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.support.annotation.VisibleForTesting
import com.cuber.devfest.data.model.Product

class DaoProvider private constructor(){

    private lateinit var room: AppDatabase
    lateinit var productDao: ProductDao

    fun init(application: Application) {
        room = Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME).build()
        productDao = room.productDao()
    }

    companion object {

        private var INSTANCE: DaoProvider? = null
        private var DATABASE_NAME = "DevFest2017"

        @JvmStatic
        fun getInstance(): DaoProvider {
            return INSTANCE ?: DaoProvider()
                    .apply { INSTANCE = this }
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

@Database(entities = arrayOf(Product::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}