package com.absut.isro.archive.data.remote.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Launcher(
    @SerializedName("id")
    val id: String
)