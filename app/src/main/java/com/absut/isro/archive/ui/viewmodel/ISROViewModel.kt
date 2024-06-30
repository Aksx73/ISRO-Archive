package com.absut.isro.archive.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.absut.isro.archive.data.model.*
import com.absut.isro.archive.domain.usecase.*
import com.absut.isro.archive.utils.Connectivity
import com.absut.isro.archive.utils.Resource
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
    // private val _spacecrafts: MutableStateFlow<State<List<Spacecraft>>> = MutableStateFlow(State.loading())
    //val spacecrafts: StateFlow<State<List<Spacecraft>>> = _spacecrafts

    var spacecrafts by mutableStateOf<State<List<Spacecraft>>>(State.loading())
        private set

    fun getSpacecrafts() {
        viewModelScope.launch {
            spacecrafts = State.loading()
            getSpacecraftUseCase.execute()
                .map { resource ->
                    State.fromResource(resource)
                }.collect { state ->
                    // _spacecrafts.value = state
                    spacecrafts = state
                }
        }
    }

    /**
     * Launchers
     */
    var launchers by mutableStateOf<State<List<Launcher>>>(State.loading())
        private set

    fun getLaunchers() = viewModelScope.launch {
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

    var customerSatellites by mutableStateOf<State<List<CustomerSatellite>>>(State.loading())
        private set

    fun getCustomerSatellites() = viewModelScope.launch {
        getCustomerSatellitesUseCase.execute()
            .map { response ->
                State.fromResource(response)
            }.collect { state ->
                customerSatellites = state
            }
    }

    /**
     * Centres
     */

    var centres by mutableStateOf<State<List<Centre>>>(State.loading())
        private set

    fun getCentres() = viewModelScope.launch {
        getCentersUseCase.execute()
            .map { response ->
                State.fromResource(response)
            }.collect { state ->
                centres = state
            }
    }

}