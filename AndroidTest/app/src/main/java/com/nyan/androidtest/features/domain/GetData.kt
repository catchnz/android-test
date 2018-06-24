package com.nyan.androidtest.features.domain

import com.nyan.androidtest.core.interactor.UseCase
import com.nyan.androidtest.features.viewmodels.Data
import com.nyan.androidtest.features.domain.repository.DataRepository
import javax.inject.Inject

class GetData
@Inject constructor(private val dataRepository: DataRepository) : UseCase<List<Data>, UseCase.None>() {

    override suspend fun run(params: None) = dataRepository.getData()

}
