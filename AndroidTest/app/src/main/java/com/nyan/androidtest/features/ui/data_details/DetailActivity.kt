package com.nyan.androidtest.features.ui.data_details

import android.content.Context
import android.content.Intent
import com.nyan.androidtest.core.platform.BaseActivity
import com.nyan.androidtest.features.ui.DataView

class DetailActivity : BaseActivity() {

    override fun fragment() = DetailFragment.showData(intent.getParcelableExtra(INTENT_EXTRA_PARAM_DATA))

    companion object {
        private const val INTENT_EXTRA_PARAM_DATA = "INTENT_EXTRA_PARAM_DATA"


        fun open(context: Context, dataView: DataView) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_DATA, dataView)
            context.startActivity(intent)
        }
    }

}
