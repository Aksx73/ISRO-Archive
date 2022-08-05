package com.absut.isro.archive.data.remote.api

import com.absut.isro.archive.data.model.CenterList
import com.absut.isro.archive.data.model.CustomerSatelliteList
import com.absut.isro.archive.data.model.LauncherList
import com.absut.isro.archive.data.model.SpacecraftList
import retrofit2.Response
import retrofit2.http.GET

interface ISROApi {

    @GET("api/spacecrafts")
    suspend fun getSpacecrafts():Response<SpacecraftList>

    @GET("api/launchers")
    suspend fun getLaunchers():Response<LauncherList>

    @GET("api/customer_satellites")
    suspend fun getCustomerSatellites():Response<CustomerSatelliteList>

    @GET("api/centres")
    suspend fun getCentres():Response<CenterList>

}