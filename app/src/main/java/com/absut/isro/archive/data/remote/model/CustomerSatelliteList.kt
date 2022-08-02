package com.absut.isro.archive.data.remote.model


import com.google.gson.annotations.SerializedName

data class CustomerSatelliteList(
    @SerializedName("customer_satellites")
    val customerSatellites: List<CustomerSatellite>
)