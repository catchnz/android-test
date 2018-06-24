package com.nyan.androidtest.features.domain.repository

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.willReturn
import com.nyan.androidtest.core.exceptions.Failure
import com.nyan.androidtest.core.functional.Either
import com.nyan.androidtest.core.platform.NetworkHandler
import com.nyan.androidtest.features.domain.GitHubDataServices
import com.nyan.androidtest.features.domain.repository.entity.DataEntity
import com.nyan.androidtest.features.viewmodels.Data
import com.nyan.androidtest.core.functional.Either.Right
import com.nyanlh.cleanarchitecturelab.UnitTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Call
import retrofit2.Response

class DataRepositoryTest : UnitTest() {

    private lateinit var repository: DataRepository.DataRepositoryImpl
    @Mock
    private
    lateinit var networkHandler: NetworkHandler
    @Mock
    private
    lateinit var service: GitHubDataServices

    @Mock
    private lateinit var dataResponse: Response<List<DataEntity>>
    @Mock
    private lateinit var dataCall: Call<List<DataEntity>>

    @Before
    fun setUp() {
        repository = DataRepository.DataRepositoryImpl(networkHandler, service)
    }

    @Test
    fun `should return empty list if server return null`() {
        given(networkHandler.isConnected).willReturn(true)
        given(dataResponse.body()).willReturn(null)
        given(dataResponse.isSuccessful).willReturn(true)
        given { dataCall.execute() }.willReturn(dataResponse)
        given { service.getData() }.willReturn(dataCall)
        val data = repository.getData()
        data shouldEqual Right(emptyList<Data>())
        verify(service).getData()
    }

    @Test
    fun `should get data list from github service`() {
        given(networkHandler.isConnected).willReturn(true)
        given(dataResponse.body()).willReturn(listOf(dataEntity1, dataEntity2, dataEntity3))
        given(dataResponse.isSuccessful).willReturn(true)
        given { dataCall.execute() }.willReturn(dataResponse)
        given { service.getData() }.willReturn(dataCall)
        val data = repository.getData()
        data.isRight shouldEqual true
        data shouldEqual Right(listOf(data1, data2, data3))
        verify(service).getData()

    }

    @Test
    fun `should return network failure when no connection`() {
        given(networkHandler.isConnected).willReturn(false)
        val data = repository.getData()

        data shouldBeInstanceOf Either::class.java
        data.isLeft shouldBe true
        data.either({
            it shouldBeInstanceOf Failure.NetworkConnection::class.java
        }, {})
        verifyZeroInteractions(service)
    }

    @Test
    fun `should return network failure when undefined connection`() {
        given(networkHandler.isConnected).willReturn(null)
        val data = repository.getData()

        data shouldBeInstanceOf Either::class.java
        data.isLeft shouldBe true
        data.either({
            it shouldBeInstanceOf Failure.NetworkConnection::class.java
        }, {})
        verifyZeroInteractions(service)
    }

    @Test
    fun `should return server error if response if false`(){
        given(networkHandler.isConnected).willReturn(true)
        given(dataResponse.isSuccessful).willReturn(false)

        val data = repository.getData()
        data shouldBeInstanceOf Either::class.java
        data.isLeft shouldBe true
        data.either({
            it shouldBeInstanceOf Failure.ServerError::class.java
        }, {})

    }


    private val dataEntity1 = DataEntity(1, "Title1", "SubTitle1", "Content")
    private val dataEntity2 = DataEntity(2, "Title2", "SubTitle2", "Content")
    private val dataEntity3 = DataEntity(3, "Title3", "SubTitle3", "Content")

    private val data1 = Data(1, "Title1", "SubTitle1", "Content")
    private val data2 = Data(2, "Title2", "SubTitle2", "Content")
    private val data3 = Data(3, "Title3", "SubTitle3", "Content")
}