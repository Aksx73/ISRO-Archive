package com.absut.isro.archive.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.absut.isro.archive.data.remote.model.*
import com.absut.isro.archive.domain.usecase.*
import com.absut.isro.archive.utils.Connectivity
import com.absut.isro.archive.utils.Resource
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class ISROViewModel(
    private val app: Application,
    private val getSpacecraftUseCase: GetSpacecraftUseCase,
    private val getLaunchersUseCase: GetLaunchersUseCase,
    private val getCentersUseCase: GetCentersUseCase,
    private val getCustomerSatellitesUseCase: GetCustomerSatellitesUseCase
) : AndroidViewModel(app) {

    val spacecrafts: MutableLiveData<Resource<SpacecraftList>> = MutableLiveData()
    val launchers: MutableLiveData<Resource<LauncherList>> = MutableLiveData()
    val customerSatellites: MutableLiveData<Resource<CustomerSatelliteList>> = MutableLiveData()
    val centers: MutableLiveData<Resource<CenterList>> = MutableLiveData()


    fun getSpacecrafts() = viewModelScope.launch {
        spacecrafts.postValue(Resource.Loading())
        try {
            if(Connectivity.isNetworkAvailable(app)) {
                val response = getSpacecraftUseCase.execute()
                if (response.data !=null){
                    spacecrafts.postValue(response)
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


}