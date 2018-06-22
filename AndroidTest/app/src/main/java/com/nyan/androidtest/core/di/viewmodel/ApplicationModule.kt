package com.nyan.androidtest.core.di.viewmodel

import android.content.Context
import dagger.Module
import com.nyan.androidtest.AndroidApplication
import com.nyan.androidtest.BuildConfig
import com.nyan.androidtest.features.DataRepository
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/catchnz/android-test/master/data/")
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideDataRepository(source: DataRepository.DataRepositoryImpl): DataRepository = source
}