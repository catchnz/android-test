package catchdesign.co.nz.androidtest

import android.app.Application
import catchdesign.co.nz.androidtest.broker.DataBroker
import catchdesign.co.nz.androidtest.broker.DataBrokerImpl


class App : Application() , AppComponent {

    private var dataBroker : DataBroker? = null

    override fun getDataBroker(): DataBroker {
        if (dataBroker == null) {
            dataBroker = DataBrokerImpl(this)
        }

        return dataBroker as DataBroker
    }

}
