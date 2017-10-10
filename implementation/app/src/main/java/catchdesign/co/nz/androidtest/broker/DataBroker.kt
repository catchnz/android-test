package catchdesign.co.nz.androidtest.broker

import catchdesign.co.nz.androidtest.model.DataObject
import io.reactivex.Flowable

interface DataBroker {

    fun getData(): Flowable<List<DataObject>>

}