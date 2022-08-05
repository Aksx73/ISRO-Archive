package com.absut.isro.archive.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.absut.isro.archive.data.model.Launcher
import com.absut.isro.archive.data.model.Spacecraft
import kotlinx.coroutines.flow.Flow

@Dao
interface LauncherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLaunchers(launchers: List<Launcher>)

    @Query("SELECT * FROM launchers")
    fun getLaunchers():Flow<List<Launcher>>

}