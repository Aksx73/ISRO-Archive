package com.absut.isro.archive.di

import com.absut.isro.archive.domain.repository.ISRORepository
import com.absut.isro.archive.domain.usecase.GetCentersUseCase
import com.absut.isro.archive.domain.usecase.GetCustomerSatellitesUseCase
import com.absut.isro.archive.domain.usecase.GetLaunchersUseCase
import com.absut.isro.archive.domain.usecase.GetSpacecraftUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesGetCentersUseCase(isroRepository: ISRORepository): GetCentersUseCase {
        return GetCentersUseCase(isroRepository)
    }

    @Provides
    @Singleton
    fun providesGetSpacecraftUseCase(isroRepository: ISRORepository): GetSpacecraftUseCase {
        return GetSpacecraftUseCase(isroRepository)
    }

    @Provides
    @Singleton
    fun providesGetLauncherUseCase(isroRepository: ISRORepository): GetLaunchersUseCase {
        return GetLaunchersUseCase(isroRepository)
    }

    @Provides
    @Singleton
    fun providesGetCustomerSatelliteUseCase(isroRepository: ISRORepository): GetCustomerSatellitesUseCase {
        return GetCustomerSatellitesUseCase(isroRepository)
    }

}