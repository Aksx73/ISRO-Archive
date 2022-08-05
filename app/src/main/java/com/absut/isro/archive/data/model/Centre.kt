package com.absut.isro.archive.data.model


import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Keep
@Entity(tableName = "centres")
data class Centre(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("Place")
    val place: String?,

    @SerializedName("State")
    val state: String?
)