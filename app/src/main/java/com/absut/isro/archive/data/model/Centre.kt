package com.absut.isro.archive.data.model


import com.google.gson.annotations.SerializedName

data class Centre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("Place")
    val place: String,
    @SerializedName("State")
    val state: String
)