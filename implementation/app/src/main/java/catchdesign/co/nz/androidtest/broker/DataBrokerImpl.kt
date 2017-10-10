package catchdesign.co.nz.androidtest.broker

import android.content.Context
import catchdesign.co.nz.androidtest.R
import catchdesign.co.nz.androidtest.model.DataObject
import com.google.gson.Gson
import io.reactivex.Flowable
import java.util.*

class DataBrokerImpl(val context: Context) : DataBroker {

    override fun getData(): Flowable<List<DataObject>> = Flowable.fromCallable {
        Arrays.asList(*Gson().fromJson(context.resources.openRawResource(R.raw.data)
                .bufferedReader().use { it.readText() }, Array<DataObject>::class.java))
    }
}
