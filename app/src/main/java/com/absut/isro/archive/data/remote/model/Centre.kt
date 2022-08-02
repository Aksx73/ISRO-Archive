package com.absut.isro.archive.data.remote.model


import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
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