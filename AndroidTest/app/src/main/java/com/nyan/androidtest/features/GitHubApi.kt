package com.nyan.androidtest.features


import com.nyan.androidtest.features.models.DataEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GitHubApi {
    companion object {
        private const val DATA = "data.json"

    }

    @GET(DATA)
    fun getData(): Call<List<DataEntity>>
}