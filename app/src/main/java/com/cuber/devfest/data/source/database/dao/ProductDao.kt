package com.cuber.devfest.data.source.database.dao

import android.arch.persistence.room.*
import com.cuber.devfest.data.model.Product
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(vararg products: Product):Completable

    @Delete
    fun deleteProduct(vararg products: Product):Completable

    @Query("SELECT * FROM products")
    fun loadAllProduct(): Single<List<Product>>

    @Query("SELECT * FROM products WHERE id = :productId LIMIT 1")
    fun loadProductById(productId: String): Single<Product>
}