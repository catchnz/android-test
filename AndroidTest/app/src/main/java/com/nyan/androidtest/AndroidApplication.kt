package com.nyan.androidtest

import android.app.Application
import com.nyan.androidtest.core.di.viewmodel.ApplicationComponent
import com.nyan.androidtest.core.di.viewmodel.ApplicationModule
import com.nyan.androidtest.core.di.viewmodel.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary

class AndroidApplication : Application() {
    val applicationComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        this.initializeLeakDetection()
    }

    private fun injectMembers() = applicationComponent.inject(this)

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}
