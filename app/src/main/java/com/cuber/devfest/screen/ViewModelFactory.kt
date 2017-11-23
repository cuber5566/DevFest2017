package com.cuber.devfest.screen

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import com.cuber.devfest.data.ProductRepository
import com.cuber.devfest.screen.product.list.ProductListViewModel

class ViewModelFactory private constructor(

        private var application: Application,
        private var productRepository: ProductRepository

) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return with(modelClass) {
            when {
                modelClass.isAssignableFrom(ProductListViewModel::class.java) -> ProductListViewModel(application, productRepository)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(

                            application,
                            ProductRepository.getInstance())

                            .also { INSTANCE = it }
                }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}