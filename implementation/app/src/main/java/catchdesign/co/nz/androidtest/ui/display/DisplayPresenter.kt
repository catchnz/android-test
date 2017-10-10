package catchdesign.co.nz.androidtest.ui.display

import catchdesign.co.nz.androidtest.AppComponent
import catchdesign.co.nz.androidtest.model.DataObject
import catchdesign.co.nz.androidtest.mvp.MvpPresenter


class DisplayPresenter(appComponent: AppComponent, private val dataObject: DataObject) : MvpPresenter<DisplayView>(appComponent) {

    override fun onViewAttached() {
        super.onViewAttached()
        view!!.setContent(dataObject.content)
    }
}
