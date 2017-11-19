package com.cuber.devfest.data.source.database.dao

import android.arch.persistence.room.*
import com.cuber.devfest.data.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(vararg products: Product)

    @Delete
    fun deleteProduct(vararg products: Product)

    @Query("SELECT * FROM products")
    fun loadAllUsers(): List<Product>

    @Query("SELECT * FROM products WHERE id = :productId LIMIT 1")
    fun loadProductById(productId: String): Product?
}