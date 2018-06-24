package com.nyan.androidtest.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nyan.androidtest.features.viewmodels.DataListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DataListViewModel::class)
    abstract fun bindDataListViewModel(dataListViewModel: DataListViewModel): ViewModel

}