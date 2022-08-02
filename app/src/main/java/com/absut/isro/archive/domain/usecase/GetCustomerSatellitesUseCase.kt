package com.absut.isro.archive.domain.usecase

import com.absut.isro.archive.data.remote.model.CustomerSatelliteList
import com.absut.isro.archive.domain.repository.ISRORepository
import com.absut.isro.archive.utils.Resource

class GetCustomerSatellitesUseCase(private val isroRepository: ISRORepository)  {

    suspend fun execute(): Resource<CustomerSatelliteList> {
        return isroRepository.getCustomerSatellites()
    }
}