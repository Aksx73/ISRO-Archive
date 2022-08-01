package com.absut.isro.archive.data.model


import com.google.gson.annotations.SerializedName

data class Spacecraft(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)