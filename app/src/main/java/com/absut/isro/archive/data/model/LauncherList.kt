package com.absut.isro.archive.data.model


import com.google.gson.annotations.SerializedName

data class LauncherList(
    @SerializedName("launchers")
    val launchers: List<Launcher>
)