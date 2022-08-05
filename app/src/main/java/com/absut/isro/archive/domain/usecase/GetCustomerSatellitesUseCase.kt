package com.absut.isro.archive.domain.usecase

import com.absut.isro.archive.data.model.CustomerSatellite
import com.absut.isro.archive.data.model.CustomerSatelliteList
import com.absut.isro.archive.domain.repository.ISRORepository
import com.absut.isro.archive.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetCustomerSatellitesUseCase(private val isroRepository: ISRORepository)  {

    suspend fun execute():Flow<Resource<List<CustomerSatellite>>> {
        return isroRepository.getCustomerSatellites()
    }
}