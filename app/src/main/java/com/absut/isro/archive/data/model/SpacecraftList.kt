package com.absut.isro.archive.data.model


import com.google.gson.annotations.SerializedName

data class SpacecraftList(
    @SerializedName("spacecrafts")
    val spacecrafts: List<Spacecraft>
)