package com.nyan.androidtest.features.domain

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.willReturn
import com.nyan.androidtest.core.functional.Either
import com.nyan.androidtest.core.interactor.UseCase
import com.nyan.androidtest.features.domain.repository.DataRepository
import com.nyan.androidtest.features.viewmodels.Data
import com.nyanlh.cleanarchitecturelab.UnitTest
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock

class GetDataTest : UnitTest() {

    lateinit var getData: GetData

    @Mock
    lateinit var dataRepository: DataRepository

    @Before
    fun setUp() {
        given { dataRepository.getData() }.willReturn(Either.Right(listOf(Data.empty())))
        getData = GetData(dataRepository)
    }

    @Test
    fun shouldGetDataFromRepository() {
        runBlocking { getData.run(UseCase.None()) }
        verify(dataRepository).getData()
        verifyNoMoreInteractions(dataRepository)
    }
}