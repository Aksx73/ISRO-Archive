package com.absut.isro.archive.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class CenterList(
    @SerializedName("centres")
    val centres: List<Centre>
)