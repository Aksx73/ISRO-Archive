package com.absut.isro.archive.data.model


import com.google.gson.annotations.SerializedName

data class CustomerSatellite(
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("launch_date")
    val launchDate: String,
    @SerializedName("launcher")
    val launcher: String,
    @SerializedName("mass")
    val mass: String
)