package com.absut.isro.archive.data.repository

import android.util.Log
import com.absut.isro.archive.data.remote.ISROApi
import com.absut.isro.archive.data.remote.model.*
import com.absut.isro.archive.domain.repository.ISRORepository
import com.absut.isro.archive.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ISRORepositoryImpl(private val isroApi: ISROApi) : ISRORepository {

    override suspend fun getSpacecrafts(): Resource<SpacecraftList> {
         return responseToResource(isroApi.getSpacecrafts())
    }

    override suspend fun getLaunchers(): Resource<LauncherList> {
          return responseToResource(isroApi.getLaunchers())
    }

    override suspend fun getCustomerSatellites(): Resource<CustomerSatelliteList> {
         return responseToResource(isroApi.getCustomerSatellites())
    }

    override suspend fun getCenters(): Resource<CenterList> {
         return responseToResource(isroApi.getCentres())
    }


    suspend fun getSpacecraftsFromAPI(): List<Spacecraft> {
        lateinit var spacecraftList: List<Spacecraft>
        try {
            val response = isroApi.getSpacecrafts()
            if (response.isSuccessful){
                val body = response.body()
                body?.let {
                    spacecraftList = body.spacecrafts

                    //todo save data to local database
                    //todo
                }

            }


        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
        return spacecraftList
    }




    /**
     * Function which returns $Resource type for state management(loading/error/success) from
     * retrofit api response
     * */
    private fun <T : Any> responseToResource(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                //todo save data to local database
                //todo
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

}