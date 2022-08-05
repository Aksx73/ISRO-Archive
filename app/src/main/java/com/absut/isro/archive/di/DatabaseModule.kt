package com.absut.isro.archive.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.absut.isro.archive.data.local.ISRODatabase
import com.absut.isro.archive.data.local.dao.CentresDao
import com.absut.isro.archive.data.local.dao.LauncherDao
import com.absut.isro.archive.data.local.dao.SatellitesDao
import com.absut.isro.archive.data.local.dao.SpacecraftDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(app: Application): ISRODatabase {
        return Room.databaseBuilder(app, ISRODatabase::class.java, "isro_db").build()
    }

    @Provides
    @Singleton
    fun providesSpacecraftDao(database: ISRODatabase): SpacecraftDao{
        return database.getSpacecraftDao()
    }

    @Provides
    @Singleton
    fun providesLauncherDao(database: ISRODatabase): LauncherDao{
        return database.getLauncherDao()
    }

    @Provides
    @Singleton
    fun providesSatelliteDao(database: ISRODatabase): SatellitesDao{
        return database.getSatelliteDao()
    }

    @Provides
    @Singleton
    fun providesCentreDao(database: ISRODatabase): CentresDao{
        return database.getCentreDao()
    }
}