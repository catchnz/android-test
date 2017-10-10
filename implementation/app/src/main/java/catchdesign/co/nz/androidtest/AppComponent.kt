package catchdesign.co.nz.androidtest

import catchdesign.co.nz.androidtest.broker.DataBroker

interface AppComponent {

    fun getDataBroker(): DataBroker

}