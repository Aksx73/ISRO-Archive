package com.absut.isro.archive.data.repository.datasourceImpl

import com.absut.isro.archive.data.api.ISROApiService
import com.absut.isro.archive.data.model.CenterList
import com.absut.isro.archive.data.model.CustomerSatelliteList
import com.absut.isro.archive.data.model.LauncherList
import com.absut.isro.archive.data.model.SpacecraftList
import com.absut.isro.archive.data.repository.datasource.ISRORemoteDataSource
import retrofit2.Response

class ISRORemoteDataSourceImpl(private val isroApiService: ISROApiService) : ISRORemoteDataSource {

    override suspend fun getSpacecrafts(): Response<SpacecraftList> {
       return isroApiService.getSpacecrafts()
    }

    override suspend fun getLaunchers(): Response<LauncherList> {
        return isroApiService.getLaunchers()
    }

    override suspend fun getCustomerSatellites(): Response<CustomerSatelliteList> {
        return isroApiService.getCustomerSatellites()
    }

    override suspend fun getCenters(): Response<CenterList> {
        return isroApiService.getCentres()
    }
}