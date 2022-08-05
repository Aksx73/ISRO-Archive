package com.absut.isro.archive.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "spacecrafts")
data class Spacecraft(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?
)