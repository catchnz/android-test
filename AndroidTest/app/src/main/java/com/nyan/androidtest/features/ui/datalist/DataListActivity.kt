package com.nyan.androidtest.features.ui.datalist

import android.content.Context
import android.content.Intent
import com.nyan.androidtest.core.platform.BaseActivity

class DataListActivity : BaseActivity() {
    override fun fragment() = DataListFragment()

    companion object {
        fun callingIntent(context: Context) = Intent(context, DataListActivity::class.java)
    }

}