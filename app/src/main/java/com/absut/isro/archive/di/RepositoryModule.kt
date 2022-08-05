package com.absut.isro.archive.di

import com.absut.isro.archive.data.local.dao.CentresDao
import com.absut.isro.archive.data.local.dao.LauncherDao
import com.absut.isro.archive.data.local.dao.SatellitesDao
import com.absut.isro.archive.data.local.dao.SpacecraftDao
import com.absut.isro.archive.data.remote.api.ISROApi
import com.absut.isro.archive.data.repository.ISRORepositoryImpl
import com.absut.isro.archive.domain.repository.ISRORepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesIsroRepository(
        isroApi: ISROApi,
        spacecraftDao: SpacecraftDao,
        launcherDao: LauncherDao,
        satellitesDao: SatellitesDao,
        centresDao: CentresDao
    ): ISRORepository {
        return ISRORepositoryImpl(isroApi, spacecraftDao, launcherDao, satellitesDao, centresDao)
    }


}