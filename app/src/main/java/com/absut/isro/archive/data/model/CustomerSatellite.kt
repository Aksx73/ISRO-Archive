package com.absut.isro.archive.data.model


import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Keep
@Entity(tableName = "customer_satellites")
data class CustomerSatellite(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("_")
    var id:Int?,

    @SerializedName("country")
    val country: String?,

    @ColumnInfo(name = "name")
    @SerializedName("id")
    val name: String?,

    @SerializedName("launch_date")
    val launchDate: String?,

    @SerializedName("launcher")
    val launcher: String?,

    @SerializedName("mass")
    val mass: String?
)