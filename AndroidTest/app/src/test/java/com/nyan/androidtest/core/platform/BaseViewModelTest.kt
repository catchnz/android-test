package com.nyan.androidtest.core.platform

import android.arch.lifecycle.MutableLiveData
import com.nyan.androidtest.core.exceptions.Failure
import com.nyanlh.cleanarchitecturelab.AndroidTest
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Assert.*
import org.junit.Test

class BaseViewModelTest : AndroidTest() {

    @Test
    fun handleFailure() {
        var vm = MyViewModel()
        vm.handleError(Failure.NetworkConnection())

        val failure = vm.failure
        val error = vm.failure.value
        failure shouldBeInstanceOf MutableLiveData::class.java
        error shouldBeInstanceOf Failure.NetworkConnection::class.java


    }

    private class MyViewModel : BaseViewModel() {
        fun handleError(failure: Failure) = handleFailure(failure)
    }
}