package com.absut.isro.archive.domain.repository

import com.absut.isro.archive.data.model.*
import com.absut.isro.archive.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ISRORepository {

    suspend fun getSpacecrafts(): Flow<Resource<List<Spacecraft>>>

    suspend fun getLaunchers(): Flow<Resource<List<Launcher>>>

    suspend fun getCustomerSatellites(): Flow<Resource<List<CustomerSatellite>>>

    suspend fun getCenters(): Flow<Resource<List<Centre>>>

}