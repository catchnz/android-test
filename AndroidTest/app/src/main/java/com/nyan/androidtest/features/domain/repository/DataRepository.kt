package com.nyan.androidtest.features.domain.repository

import com.nyan.androidtest.core.exceptions.Failure
import com.nyan.androidtest.core.functional.Either
import com.nyan.androidtest.core.platform.NetworkHandler
import com.nyan.androidtest.features.viewmodels.Data
import com.nyan.androidtest.features.domain.GitHubDataServices
import retrofit2.Call
import javax.inject.Inject

interface DataRepository {
    fun getData(): Either<Failure, List<Data>>
    class DataRepositoryImpl
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: GitHubDataServices) : DataRepository {
        override fun getData(): Either<Failure, List<Data>> {
            return when (networkHandler.isConnected) {
                true -> request(service.getData(), { it.map { it.toData() } }, emptyList())
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default)))
                    false -> Either.Left(Failure.ServerError())
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError())
            }
        }
    }
}