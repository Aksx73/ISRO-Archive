package com.absut.isro.archive.data.repository

import com.absut.isro.archive.data.model.CenterList
import com.absut.isro.archive.data.model.CustomerSatelliteList
import com.absut.isro.archive.data.model.LauncherList
import com.absut.isro.archive.data.model.SpacecraftList
import com.absut.isro.archive.data.repository.datasource.ISRORemoteDataSource
import com.absut.isro.archive.domain.repository.ISRORepository
import com.absut.newsapiclient.domain.util.Resource
import retrofit2.Response

class ISRORepositoryImpl(private val isroRemoteDataSource: ISRORemoteDataSource) : ISRORepository {

    override suspend fun getSpacecrafts(): Resource<SpacecraftList> {
        return responseToResource(isroRemoteDataSource.getSpacecrafts())
    }

    override suspend fun getLaunchers(): Resource<LauncherList> {
       return responseToResource(isroRemoteDataSource.getLaunchers())
    }

    override suspend fun getCustomerSatellites(): Resource<CustomerSatelliteList> {
        return responseToResource(isroRemoteDataSource.getCustomerSatellites())
    }

    override suspend fun getCenters(): Resource<CenterList> {
        return responseToResource(isroRemoteDataSource.getCenters())
    }


    /**
    * Function which returns $Resource type for state management(loading/error/success) from
     * retrofit api response
    * */
    private fun <T : Any> responseToResource(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}