package com.cuber.devfest.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "products")
data class Product(

        @PrimaryKey var id: String,
        var name: String,
        var content: String,
        var price: Int
)
