package com.absut.isro.archive.ui

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.absut.isro.archive.data.model.*
import com.absut.isro.archive.domain.usecase.*
import com.absut.isro.archive.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ISROViewModel @Inject constructor(
    private val app: Application,
    private val getSpacecraftUseCase: GetSpacecraftUseCase,
    private val getLaunchersUseCase: GetLaunchersUseCase,
    private val getCentersUseCase: GetCentersUseCase,
    private val getCustomerSatellitesUseCase: GetCustomerSatellitesUseCase
) : AndroidViewModel(app) {

    /**
     * Spacecrafts
     */
    private val _spacecrafts: MutableStateFlow<State<List<Spacecraft>>> =
        MutableStateFlow(State.loading())
    val spacecrafts: StateFlow<State<List<Spacecraft>>> = _spacecrafts.asStateFlow()

    fun getSpacecrafts() = viewModelScope.launch {
        getSpacecraftUseCase.execute()
            .map { resource ->
                State.fromResource(resource)
            }.collect { state ->
                _spacecrafts.value = state
            }
    }

    /**
     * Launchers
     */
    var launchers by mutableStateOf<State<List<Launcher>>>(State.loading())
        private set

    fun getLaunchers() = viewModelScope.launch {
        launchers = State.loading()
        getLaunchersUseCase.execute()
            .map { response ->
                State.fromResource(response)
            }.collect { state ->
                launchers = state
            }
    }

    /**
     * Customer_Satellites
     */
    private val _customerSatellites: MutableStateFlow<State<List<CustomerSatellite>>> =
        MutableStateFlow(State.loading())
    val customerSatellites: StateFlow<State<List<CustomerSatellite>>> =
        _customerSatellites.asStateFlow()

    fun getCustomerSatellites() = viewModelScope.launch {
        getCustomerSatellitesUseCase.execute()
            .map { response ->
                State.fromResource(response)
            }.collect { state ->
                _customerSatellites.value = state
            }
    }

    /**
     * Centres
     */
    var centres by mutableStateOf<State<List<Centre>>>(State.loading())
        private set

    fun getCentres() = viewModelScope.launch {
        centres = State.loading()
        getCentersUseCase.execute()
            .map { response ->
                State.fromResource(response)
            }.collect { state ->
                centres = state
            }
    }

}