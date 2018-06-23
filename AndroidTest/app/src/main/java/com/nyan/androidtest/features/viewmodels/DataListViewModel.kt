package com.nyan.androidtest.features.viewmodels

import android.arch.lifecycle.MutableLiveData
import com.nyan.androidtest.core.interactor.UseCase
import com.nyan.androidtest.core.platform.BaseViewModel
import com.nyan.androidtest.features.ui.DataView
import com.nyan.androidtest.features.domain.GetData
import javax.inject.Inject

class DataListViewModel
@Inject constructor(private val getData: GetData) : BaseViewModel() {
    var data: MutableLiveData<List<DataView>> = MutableLiveData()

    fun loadData() {
        getData.execute({ it.either(::handleFailure, ::handleDataList) }, UseCase.None())

    }

    private fun handleDataList(dataList: List<Data>) {
        this.data.value = dataList.map { DataView(it.id, it.title, it.subTitle, it.content) }
    }

}