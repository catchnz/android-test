package catchdesign.co.nz.androidtest.ui.main

import catchdesign.co.nz.androidtest.AppComponent
import catchdesign.co.nz.androidtest.model.DataObject
import catchdesign.co.nz.androidtest.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MainPresenter(appComponent: AppComponent) : MvpPresenter<MainView>(appComponent) {
    fun onMainViewClicked() {
        view!!.showLoading()
        appComponent.getDataBroker()
                .getData()
                .delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    setData(it)
                }
    }

    private fun setData(it: List<DataObject>) {
        view?.setData(it)
    }
}

