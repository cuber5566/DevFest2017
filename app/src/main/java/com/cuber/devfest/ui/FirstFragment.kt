package com.cuber.devfest.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cuber.devfest.R
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A placeholder fragment containing a simple view.
 */
class FirstFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var fragments = fragmentManager.fragments;
        mainViewModel = ViewModelProviders.of(activity).get(MainViewModel::class.java)
        mainViewModel.test()
        mainViewModel.data.observe(this, Observer {
            number_test.text = it
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        number_test.text = "1"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("FirstFragment", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("FirstFragment", "onDestroy")
    }

    public fun setText(text: String) {
        number_test.text = "3"
    }
}
