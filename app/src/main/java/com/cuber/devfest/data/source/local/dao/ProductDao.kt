package com.cuber.devfest.data.source.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.cuber.devfest.data.model.Product
import io.reactivex.Single

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductList(products: List<Product>)

    @Query("DELETE FROM products")
    fun deleteAllProduct()

    @Query("SELECT * FROM products WHERE id = :productId")
    fun loadProductById(productId: String): Single<Product>

    @Query("SELECT * FROM products ")
    fun loadProductList(): Single<List<Product>>
}