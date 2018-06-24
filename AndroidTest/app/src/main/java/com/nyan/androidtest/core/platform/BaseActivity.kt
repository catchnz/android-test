package com.nyan.androidtest.core.platform

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nyan.androidtest.R.id
import com.nyan.androidtest.R.layout
import com.nyan.androidtest.core.extension.inTransaction
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_layout)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(
                id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
            savedInstanceState ?: supportFragmentManager.inTransaction {
                add(
                        id.fragmentContainer, fragment())
            }

    abstract fun fragment(): BaseFragment
}