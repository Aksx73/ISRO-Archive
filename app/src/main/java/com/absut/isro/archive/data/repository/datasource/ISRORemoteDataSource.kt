package com.absut.isro.archive.data.repository.datasource

import com.absut.isro.archive.data.model.CenterList
import com.absut.isro.archive.data.model.CustomerSatelliteList
import com.absut.isro.archive.data.model.LauncherList
import com.absut.isro.archive.data.model.SpacecraftList
import com.absut.newsapiclient.domain.util.Resource
import retrofit2.Response

interface ISRORemoteDataSource {

    suspend fun getSpacecrafts(): Response<SpacecraftList>

    suspend fun getLaunchers(): Response<LauncherList>

    suspend fun getCustomerSatellites(): Response<CustomerSatelliteList>

    suspend fun getCenters(): Response<CenterList>

}