package catchdesign.co.nz.androidtest.ui.main

import catchdesign.co.nz.androidtest.model.DataObject
import catchdesign.co.nz.androidtest.mvp.MvpView

interface MainView : MvpView {

    fun showLoading()

    fun setData(it: List<DataObject>)

}