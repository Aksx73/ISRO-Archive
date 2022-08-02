package com.absut.isro.archive.data.remote.model


import com.google.gson.annotations.SerializedName

data class CenterList(
    @SerializedName("centres")
    val centres: List<Centre>
)