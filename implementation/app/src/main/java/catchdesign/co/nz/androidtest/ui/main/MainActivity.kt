package catchdesign.co.nz.androidtest.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import catchdesign.co.nz.androidtest.AppComponent
import catchdesign.co.nz.androidtest.R
import catchdesign.co.nz.androidtest.model.DataObject
import catchdesign.co.nz.androidtest.mvp.MvpActivity
import catchdesign.co.nz.androidtest.ui.UiUtil
import catchdesign.co.nz.androidtest.ui.display.DisplayActivity

class MainActivity : MvpActivity<MainView, MainPresenter>(), MainView {

    private val mainScreen: View?
        get() {
            return findViewById(R.id.main_splash)
        }

    private val recyclerView: RecyclerView?
        get() {
            return findViewById(R.id.main_recyclerView) as RecyclerView?
        }

    private val loading : View?
        get() {
            return findViewById(R.id.main_spinner)
        }

    private val adapter = DataAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainScreen?.setOnClickListener { presenter!!.onMainViewClicked() }
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter
    }

    override fun showLoading() {
        loading!!.visibility = View.VISIBLE
    }

    override fun setData(it: List<DataObject>) {
        UiUtil.fadeOutView(mainScreen)
        adapter.update(it)
    }

    override fun createPresenter(): MainPresenter? {
        return MainPresenter(application as AppComponent)
    }
}

class DataViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleTextView: TextView
        get() {
            return itemView.findViewById(R.id.dataItem_title)
        }

    private val subtitleTextView: TextView
        get () {
            return itemView.findViewById(R.id.dataItem_subtitle)
        }

    fun setDataObject(dataObject: DataObject) {
        titleTextView.text = dataObject.title
        subtitleTextView.text = dataObject.subtitle

        itemView.setOnClickListener {
            context.startActivity(Intent(context,
                    DisplayActivity::class.java).putExtra(DisplayActivity.EXTRA_DATA_OBJECT, dataObject))
        }
    }
}

class DataAdapter(val context: Context) : RecyclerView.Adapter<DataViewHolder>() {

    private val data = ArrayList<DataObject>()

    fun update(newData: Collection<DataObject>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DataViewHolder {
        return DataViewHolder(
                context,
                LayoutInflater.from(context)
                        .inflate(R.layout.data_item, parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder?, position: Int) {
        holder?.setDataObject(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
