package com.nyan.androidtest.features

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubDataServices
@Inject constructor(retrofit: Retrofit) : GitHubApi {
    private val gitHubApi by lazy { retrofit.create(GitHubApi::class.java) }

    override fun getData() = gitHubApi.getData()
}
