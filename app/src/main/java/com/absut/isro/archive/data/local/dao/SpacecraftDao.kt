package com.absut.isro.archive.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.absut.isro.archive.data.model.Spacecraft
import kotlinx.coroutines.flow.Flow

@Dao
interface SpacecraftDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSpacecrafts(spacecraft: List<Spacecraft>)

    @Query("SELECT * FROM spacecrafts")
    fun getSpacecrafts():Flow<List<Spacecraft>>

}