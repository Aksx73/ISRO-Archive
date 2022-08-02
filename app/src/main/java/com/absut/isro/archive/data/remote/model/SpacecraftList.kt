package com.absut.isro.archive.data.remote.model


import com.google.gson.annotations.SerializedName

data class SpacecraftList(
    @SerializedName("spacecrafts")
    val spacecrafts: List<Spacecraft>
)