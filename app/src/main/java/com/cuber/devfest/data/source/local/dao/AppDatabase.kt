package com.cuber.devfest.data.source.local.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.cuber.devfest.data.model.Product

@Database(entities = arrayOf(Product::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}