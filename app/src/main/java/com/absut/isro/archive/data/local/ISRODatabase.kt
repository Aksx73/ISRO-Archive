package com.absut.isro.archive.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.absut.isro.archive.data.local.dao.CentresDao
import com.absut.isro.archive.data.local.dao.LauncherDao
import com.absut.isro.archive.data.local.dao.SatellitesDao
import com.absut.isro.archive.data.local.dao.SpacecraftDao
import com.absut.isro.archive.data.model.Centre
import com.absut.isro.archive.data.model.CustomerSatellite
import com.absut.isro.archive.data.model.Launcher
import com.absut.isro.archive.data.model.Spacecraft

@Database(
    entities = [Centre::class, Launcher::class, Spacecraft::class, CustomerSatellite::class],
    version = 1
)
abstract class ISRODatabase() : RoomDatabase() {

    abstract fun getSpacecraftDao(): SpacecraftDao

    abstract fun getLauncherDao(): LauncherDao

    abstract fun getCentreDao(): CentresDao

    abstract fun getSatelliteDao(): SatellitesDao
}