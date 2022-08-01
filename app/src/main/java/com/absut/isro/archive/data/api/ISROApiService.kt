package com.absut.isro.archive.data.api

import com.absut.isro.archive.data.model.CenterList
import com.absut.isro.archive.data.model.CustomerSatelliteList
import com.absut.isro.archive.data.model.LauncherList
import com.absut.isro.archive.data.model.SpacecraftList
import retrofit2.Response
import retrofit2.http.GET

interface ISROApiService {

    @GET("spacecrafts")
    suspend fun getSpacecrafts():Response<SpacecraftList>

    @GET("launchers")
    suspend fun getLaunchers():Response<LauncherList>

    @GET("customer_satellites")
    suspend fun getCustomerSatellites():Response<CustomerSatelliteList>

    @GET("centres")
    suspend fun getCentres():Response<CenterList>

}