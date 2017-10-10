package catchdesign.co.nz.androidtest.ui.display

import android.os.Bundle
import android.widget.TextView
import catchdesign.co.nz.androidtest.R
import catchdesign.co.nz.androidtest.model.DataObject
import catchdesign.co.nz.androidtest.mvp.MvpActivity

class DisplayActivity : MvpActivity<DisplayView, DisplayPresenter>(), DisplayView {

    private val contentView: TextView
        get() {
            return findViewById(R.id.display_content) as TextView
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
    }

    override fun setContent(content: String) {
        contentView.text = content
    }

    override fun createPresenter(): DisplayPresenter? {
        return DisplayPresenter(appComponent, intent.getParcelableExtra<DataObject>(EXTRA_DATA_OBJECT))
    }

    companion object {
        val EXTRA_DATA_OBJECT = "extra_data_object"
    }
}
