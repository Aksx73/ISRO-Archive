package com.absut.isro.archive.domain.repository

import com.absut.isro.archive.data.remote.model.*
import com.absut.isro.archive.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ISRORepository {

    suspend fun getSpacecrafts(): Resource<SpacecraftList>

    suspend fun getLaunchers(): Resource<LauncherList>

    suspend fun getCustomerSatellites(): Resource<CustomerSatelliteList>

    suspend fun getCenters(): Resource<CenterList>

}