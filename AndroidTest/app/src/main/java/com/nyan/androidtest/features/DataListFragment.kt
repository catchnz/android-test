package com.nyan.androidtest.features


import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nyan.androidtest.R
import com.nyan.androidtest.core.exceptions.Failure
import com.nyan.androidtest.core.extension.failure
import com.nyan.androidtest.core.extension.observe
import com.nyan.androidtest.core.extension.viewModel
import com.nyan.androidtest.core.platform.BaseFragment
import com.nyan.androidtest.features.models.DataView
import kotlinx.android.synthetic.main.fragment_data_list.*
import javax.inject.Inject


class DataListFragment : BaseFragment() {
    lateinit var dataListViewModel: DataListViewModel
    @Inject
    lateinit var dataListAdapter: DataListAdapter

    override fun layoutId() = R.layout.fragment_data_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        dataListViewModel = viewModel(viewModelFactory) {
            observe(data, ::renderData)
            failure(failure, ::handleFailure)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun renderData(data: List<DataView>?) {

        dataListAdapter.collection = data.orEmpty()
        swipeToRefresh.isRefreshing = false

    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = dataListAdapter
        dataListAdapter.clickListener = { data ->

            //Goto city
            Log.e("Clicked", data.toString())

        }

        swipeToRefresh.setOnRefreshListener { dataListViewModel.loadData() }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        swipeToRefresh.isRefreshing = false
        Log.e("TAG", "renderFailure ${getString(message)}")
    }
}
