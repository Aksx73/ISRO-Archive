package com.absut.isro.archive.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CustomerSatelliteList(
    @SerializedName("customer_satellites")
    val customerSatellites: List<CustomerSatellite>
)