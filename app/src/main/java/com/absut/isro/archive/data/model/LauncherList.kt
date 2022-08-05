package com.absut.isro.archive.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LauncherList(
    @SerializedName("launchers")
    val launchers: List<Launcher>
)