package com.nyan.androidtest.core.platform

import android.content.Context
import com.nyan.androidtest.core.extension.networkInfo
import javax.inject.Singleton

@Singleton
class NetworkHandler
  constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnectedOrConnecting
}