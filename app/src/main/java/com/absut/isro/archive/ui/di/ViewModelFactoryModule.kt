package com.absut.isro.archive.ui.di

import android.app.Application
import com.absut.isro.archive.domain.usecase.GetCentersUseCase
import com.absut.isro.archive.domain.usecase.GetCustomerSatellitesUseCase
import com.absut.isro.archive.domain.usecase.GetLaunchersUseCase
import com.absut.isro.archive.domain.usecase.GetSpacecraftUseCase
import com.absut.isro.archive.ui.viewmodel.ISROViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun providesFactory(
        app: Application,
        getSpacecraftUseCase: GetSpacecraftUseCase,
        getLaunchersUseCase: GetLaunchersUseCase,
        getCentersUseCase: GetCentersUseCase,
        getCustomerSatellitesUseCase: GetCustomerSatellitesUseCase
    ): ISROViewModelFactory {
        return ISROViewModelFactory(
            app,
            getSpacecraftUseCase,
            getLaunchersUseCase,
            getCentersUseCase,
            getCustomerSatellitesUseCase
        )
    }

}