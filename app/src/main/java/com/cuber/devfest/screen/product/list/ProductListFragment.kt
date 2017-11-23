package com.cuber.devfest.screen.product.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cuber.devfest.R
import com.cuber.devfest.screen.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductListFragment : Fragment() {

    private lateinit var postAdapter: PostListAdapter
    private lateinit var viewModel: ProductListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelFactory.getInstance(activity!!.application).create(ProductListViewModel::class
                .java)
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

        viewModel.productList.observe(this, Observer {
            postAdapter.productList = it
            postAdapter.notifyDataSetChanged()
        })
    }
}
