package com.example.chanx.test.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chanx on 01/10/2017.
 *
 * Provides Retrofit object for network communication
 * use Gson converter for JSON, and RxJava adapter for RxJava interface
 */

public class NetworkModule {
    public static Retrofit provideRetrofit(String url) {

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
