package com.nyan.androidtest.features.domain


import com.nyan.androidtest.features.domain.repository.entity.DataEntity
import retrofit2.Call
import retrofit2.http.GET

internal interface GitHubApi {
    companion object {
        private const val DATA = "data.json"

    }

    @GET(DATA)
    fun getData(): Call<List<DataEntity>>
}