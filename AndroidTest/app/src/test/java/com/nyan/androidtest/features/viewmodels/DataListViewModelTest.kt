package com.nyan.androidtest.features.viewmodels

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import com.nyan.androidtest.core.functional.Either
import com.nyan.androidtest.features.domain.GetData
import com.nyanlh.cleanarchitecturelab.AndroidTest
import kotlinx.coroutines.experimental.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class DataListViewModelTest : AndroidTest() {
    @Mock
    private lateinit var getData: GetData
    lateinit var dataListViewModel: DataListViewModel

    @Before
    fun setUp() {
        dataListViewModel = DataListViewModel(getData)
    }


    @Test
    fun loadData() {
        given { runBlocking { getData.run(eq(any())) } }
                .willReturn(Either.Right(listOf(data1, data2, data3)))

        dataListViewModel.data.observeForever {
            it!!.size shouldEqualTo 3
            it[0].title shouldEqualTo "Title1"
            it[0].subTitle shouldEqualTo "SubTitle1"
            it[0].content shouldEqualTo "content1"

        }
        dataListViewModel.loadData()
    }


    val data1 = Data(1, "Title1", "SubTitle1", "content1")
    val data2 = Data(2, "Title2", "SubTitle2", "content2")
    val data3 = Data(3, "Title3", "SubTitle3", "content3")


}