package com.absut.isro.archive.ui.di

import com.absut.isro.archive.data.api.ISROApiService
import com.absut.isro.archive.data.repository.datasource.ISRORemoteDataSource
import com.absut.isro.archive.data.repository.datasourceImpl.ISRORemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun providesSpacecraftRemoteDataSource(isroApiService: ISROApiService): ISRORemoteDataSource {
        return ISRORemoteDataSourceImpl(isroApiService)
    }

}