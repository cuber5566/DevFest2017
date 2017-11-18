package com.cuber.devfest.ui.product.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cuber.devfest.R
import com.cuber.devfest.data.model.Product
import kotlinx.android.synthetic.main.item_product.view.*

class PostListAdapter(private val context: Context) : RecyclerView.Adapter<PostListAdapter.ProductHolder>() {

    private var productList: List<Product>? = null

    override fun getItemCount(): Int = productList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProductHolder =
            ProductHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false))

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {

        productList!![position].run {
            holder.title.text = name
            holder.content.text = content
            holder.price.text = price.toString()
        }
    }

    inner class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.title!!
        val content = itemView.content!!
        val price = itemView.price!!
    }
}