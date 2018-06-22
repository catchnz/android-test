package com.nyan.androidtest.features

import android.content.Context
import android.content.Intent
import com.nyan.androidtest.R
import com.nyan.androidtest.core.platform.BaseActivity
import com.nyan.androidtest.core.platform.BaseFragment

class DataListActivity : BaseActivity() {
    override fun fragment() = DataListFragment()

    companion object {
        fun callingIntent(context: Context) = Intent(context, DataListActivity::class.java)
    }

}