package com.absut.isro.archive.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.absut.isro.archive.domain.usecase.GetCentersUseCase
import com.absut.isro.archive.domain.usecase.GetCustomerSatellitesUseCase
import com.absut.isro.archive.domain.usecase.GetLaunchersUseCase
import com.absut.isro.archive.domain.usecase.GetSpacecraftUseCase

@Suppress("UNCHECKED_CAST")
class ISROViewModelFactory(
    private val app: Application,
    private val getSpacecraftUseCase: GetSpacecraftUseCase,
    private val getLaunchersUseCase: GetLaunchersUseCase,
    private val getCentersUseCase: GetCentersUseCase,
    private val getCustomerSatellitesUseCase: GetCustomerSatellitesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ISROViewModel::class.java)) {
            return ISROViewModel(
                app,
                getSpacecraftUseCase,
                getLaunchersUseCase,
                getCentersUseCase,
                getCustomerSatellitesUseCase
            ) as T
        } else {
            throw Exception("Unknown view model")
        }
    }
}