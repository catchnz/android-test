package com.nyan.androidtest.features.ui.datalist


import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.dinuscxj.refresh.RecyclerRefreshLayout
import com.nyan.androidtest.R
import com.nyan.androidtest.core.exceptions.Failure
import com.nyan.androidtest.core.extension.dp
import com.nyan.androidtest.core.extension.failure
import com.nyan.androidtest.core.extension.observe
import com.nyan.androidtest.core.extension.viewModel
import com.nyan.androidtest.core.platform.BaseFragment
import com.nyan.androidtest.features.ui.DataView
import com.nyan.androidtest.features.ui.SimpleDividerItemDecoration
import com.nyan.androidtest.features.ui.data_details.DetailActivity
import com.nyan.androidtest.features.viewmodels.DataListViewModel
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
        setupSwipeToRefresh()
    }

    private fun setupSwipeToRefresh() {
        swipeToRefresh.setRefreshStyle(RecyclerRefreshLayout.RefreshStyle.PINNED)
        swipeToRefresh.setRefreshInitialOffset(15.dp);
        swipeToRefresh.setRefreshTargetOffset(60.dp)
        swipeToRefresh.setOnRefreshListener { dataListViewModel.loadData() }
    }

    private fun renderData(data: List<DataView>?) {

        dataListAdapter.collection = data.orEmpty()
        swipeToRefresh.setRefreshing(false)

    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity!!)
        recyclerView.adapter = dataListAdapter
        recyclerView.addItemDecoration(SimpleDividerItemDecoration(activity!!));
        dataListAdapter.clickListener = { data ->
            DetailActivity.open(activity!!, data)
        }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        swipeToRefresh.setRefreshing(false)
        Log.e("TAG", "renderFailure ${getString(message)}")
    }
}
