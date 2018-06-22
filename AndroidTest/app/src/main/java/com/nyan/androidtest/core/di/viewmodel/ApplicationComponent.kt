package com.nyan.androidtest.core.di.viewmodel

import com.nyan.androidtest.AndroidApplication
import com.nyan.androidtest.features.DataListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(dataListFragment: DataListFragment)
}