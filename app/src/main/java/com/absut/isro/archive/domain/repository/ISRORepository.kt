package com.absut.isro.archive.domain.repository

import com.absut.isro.archive.data.model.CenterList
import com.absut.isro.archive.data.model.CustomerSatelliteList
import com.absut.isro.archive.data.model.LauncherList
import com.absut.isro.archive.data.model.SpacecraftList
import com.absut.newsapiclient.domain.util.Resource
import retrofit2.Response

interface ISRORepository {

    suspend fun getSpacecrafts():Resource<SpacecraftList>

    suspend fun getLaunchers():Resource<LauncherList>

    suspend fun getCustomerSatellites():Resource<CustomerSatelliteList>

    suspend fun getCenters():Resource<CenterList>
}