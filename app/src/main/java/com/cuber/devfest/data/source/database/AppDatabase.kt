package com.cuber.devfest.data.source.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.cuber.devfest.data.model.Product
import com.cuber.devfest.data.source.database.dao.ProductDao

@Database(entities = arrayOf(Product::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}