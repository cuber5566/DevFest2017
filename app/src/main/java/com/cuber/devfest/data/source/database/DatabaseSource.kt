package com.cuber.devfest.data.source.database

import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.cuber.devfest.data.model.Product
import com.cuber.devfest.data.source.database.dao.ProductDao

class DatabaseSource {

    lateinit var db: AppDatabase

    fun init(application: Application) {
        db = Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME).build()
    }

    companion object {

        private var INSTANCE: DatabaseSource? = null
        private var DATABASE_NAME = "DevFest2017.db"

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

@Database(entities = arrayOf(Product::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}