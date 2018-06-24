package com.nyan.androidtest.core.platform

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nyan.androidtest.AndroidApplication
import com.nyan.androidtest.core.di.ApplicationComponent
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
    abstract fun layoutId(): Int
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(layoutId(), container, false)

    open fun onBackPressed() {}

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).applicationComponent
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}