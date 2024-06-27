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

    /* val spacecrafts: MutableLiveData<Resource<SpacecraftList>> = MutableLiveData()
     val launchers: MutableLiveData<Resource<LauncherList>> = MutableLiveData()
     val customerSatellites: MutableLiveData<Resource<CustomerSatelliteList>> = MutableLiveData()
     val centers: MutableLiveData<Resource<CenterList>> = MutableLiveData()*/

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
    private val _launchers: MutableStateFlow<State<List<Launcher>>> =
        MutableStateFlow(State.loading())
    val launchers: StateFlow<State<List<Launcher>>> = _launchers

    fun getLaunchers() = viewModelScope.launch {
        getLaunchersUseCase.execute()
            .map { response ->
                State.fromResource(response)
            }.collect { state ->
                _launchers.value = state
            }
    }

    /**
     * Customer_Satellites
     */
    private val _customerSatellites: MutableStateFlow<State<List<CustomerSatellite>>> =
        MutableStateFlow(State.loading())
    val customerSatellites: StateFlow<State<List<CustomerSatellite>>> = _customerSatellites

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
    private val _centres: MutableStateFlow<State<List<Centre>>> = MutableStateFlow(State.loading())
    val centres: StateFlow<State<List<Centre>>> = _centres

    fun getCentres() = viewModelScope.launch {
        getCentersUseCase.execute()
            .map { response ->
                State.fromResource(response)
            }.collect { state ->
                _centres.value = state
            }
    }


    /*  fun getSpacecrafts() = viewModelScope.launch {
          spacecrafts.postValue(Resource.Loading())
          try {
              if(Connectivity.isNetworkAvailable(app)) {
                  val response = getSpacecraftUseCase.execute()
                  if (response.data !=null){
                      spacecrafts.postValue(response)
                    /*  _spacecraftList.clear()
                      _spacecraftList.addAll(response.data.spacecrafts)*/
                      //todo get data from local database
                  }
              }else{
                  spacecrafts.postValue(Resource.NoConnection())
              }
          } catch (e: Exception) {
              spacecrafts.postValue(Resource.Error(e.message.toString()))
          }
      }

      fun getLaunchers() = viewModelScope.launch {
          launchers.postValue(Resource.Loading())
          try {
              if(Connectivity.isNetworkAvailable(app)) {
                  val response = getLaunchersUseCase.execute()
                  launchers.postValue(response)
              }else{
                  launchers.postValue(Resource.NoConnection())
                  //todo get data from local database
              }
          } catch (e: Exception) {
              launchers.postValue(Resource.Error(e.message.toString()))
          }
      }


      fun getCenters() = viewModelScope.launch {
          centers.postValue(Resource.Loading())
          try {
              if(Connectivity.isNetworkAvailable(app)) {
                  val response = getCentersUseCase.execute()
                  centers.postValue(response)
              }else{
                  centers.postValue(Resource.NoConnection())
                  //todo get data from local database
              }
          } catch (e: Exception) {
              centers.postValue(Resource.Error(e.message.toString()))
          }
      }

      fun getCustomerSatellites() = viewModelScope.launch {
          customerSatellites.postValue(Resource.Loading())
          try {
              if(Connectivity.isNetworkAvailable(app)) {
                  val response = getCustomerSatellitesUseCase.execute()
                  customerSatellites.postValue(response)
              }else{
                  customerSatellites.postValue(Resource.NoConnection())
                  //todo get data from local database
              }
          } catch (e: Exception) {
              customerSatellites.postValue(Resource.Error(e.message.toString()))
          }
      }
  */

}