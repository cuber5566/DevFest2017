package com.cuber.devfest.ui.product.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cuber.devfest.R
import com.cuber.devfest.data.model.Product
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductListFragment : Fragment(), ProductListContract.View {

    private lateinit var presenter: ProductListPresenter
    private lateinit var categoryId: String
    private lateinit var postAdapter: PostListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // data init
        arguments?.run {
            categoryId = getString(ARG_CATEGORY_ID)
        }

        savedInstanceState?.run {

        }
        presenter = ProductListPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_product_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView?.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            postAdapter = PostListAdapter(context)
            adapter = postAdapter
        }
//        call from presenter onSubscribe
        presenter.getProductList(categoryId)
    }

    override fun onGetProductList(productList: List<Product>) {
        postAdapter.productList = productList
        postAdapter.notifyDataSetChanged()
    }

    override fun onGetProductListError(throwable: Throwable) {
        Toast.makeText(context, getString(R.string.error_get_product_list), Toast.LENGTH_LONG).show()
    }

    companion object {
        val ARG_CATEGORY_ID = "ARG_CATEGORY_ID"
    }
}
