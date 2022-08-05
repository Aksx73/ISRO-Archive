package com.absut.isro.archive.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "launchers")
data class Launcher(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("_")
    var id:Int?,

    @ColumnInfo(name = "name")
    @SerializedName("id")
    val name: String?

)