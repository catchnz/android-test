package com.nyan.androidtest.features

import com.nyan.androidtest.core.interactor.UseCase
import com.nyan.androidtest.features.models.Data
import javax.inject.Inject

class GetData
@Inject constructor(private val dataRepository: DataRepository) : UseCase<List<Data>, UseCase.None>() {

    override suspend fun run(params: None) = dataRepository.getData()

}
