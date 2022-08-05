package com.absut.isro.archive.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.absut.isro.archive.data.model.CustomerSatellite
import com.absut.isro.archive.data.model.Spacecraft
import kotlinx.coroutines.flow.Flow

@Dao
interface SatellitesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSatellites(satellites: List<CustomerSatellite>)

    @Query("SELECT * FROM customer_satellites")
    fun getSatellites():Flow<List<CustomerSatellite>>

}