package com.absut.isro.archive.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.absut.isro.archive.data.model.CenterList
import com.absut.isro.archive.data.model.CustomerSatelliteList
import com.absut.isro.archive.data.model.LauncherList
import com.absut.isro.archive.data.model.SpacecraftList
import com.absut.isro.archive.domain.usecase.GetCentersUseCase
import com.absut.isro.archive.domain.usecase.GetCustomerSatellitesUseCase
import com.absut.isro.archive.domain.usecase.GetLaunchersUseCase
import com.absut.isro.archive.domain.usecase.GetSpacecraftUseCase
import com.absut.isro.archive.ui.utils.Connectivity
import com.absut.newsapiclient.domain.util.Resource
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


    fun getSpacecraftsList() = viewModelScope.launch {
        spacecrafts.postValue(Resource.Loading())
        try {
            if(Connectivity.isNetworkAvailable(app)) {
                val response = getSpacecraftUseCase.execute()
                spacecrafts.postValue(response)
            }else{
                spacecrafts.postValue(Resource.Error("No internet connection"))
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
                launchers.postValue(Resource.Error("No internet connection"))
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
                centers.postValue(Resource.Error("No internet connection"))
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
                customerSatellites.postValue(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            customerSatellites.postValue(Resource.Error(e.message.toString()))
        }
    }


}