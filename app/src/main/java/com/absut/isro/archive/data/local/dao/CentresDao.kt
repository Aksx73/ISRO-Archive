package com.absut.isro.archive.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.absut.isro.archive.data.model.Centre
import com.absut.isro.archive.data.model.Spacecraft
import kotlinx.coroutines.flow.Flow

@Dao
interface CentresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCentres(centre: List<Centre>)

    @Query("SELECT * FROM centres")
    fun getCentres():Flow<List<Centre>>

}