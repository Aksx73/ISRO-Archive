package com.absut.isro.archive.data.repository

import com.absut.isro.archive.data.local.dao.CentresDao
import com.absut.isro.archive.data.local.dao.LauncherDao
import com.absut.isro.archive.data.local.dao.SatellitesDao
import com.absut.isro.archive.data.local.dao.SpacecraftDao
import com.absut.isro.archive.data.model.*
import com.absut.isro.archive.data.remote.api.ISROApi
import com.absut.isro.archive.domain.repository.ISRORepository
import com.absut.isro.archive.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

@ExperimentalCoroutinesApi
class ISRORepositoryImpl(
    private val isroApi: ISROApi,
    private val spacecraftDao: SpacecraftDao,
    private val launcherDao: LauncherDao,
    private val satellitesDao: SatellitesDao,
    private val centresDao: CentresDao
) : ISRORepository {

    override suspend fun getSpacecrafts(): Flow<Resource<List<Spacecraft>>> {
        return object : NetworkBoundResource<List<Spacecraft>, SpacecraftList>() {

            override suspend fun saveRemoteData(response: SpacecraftList) = spacecraftDao.addSpacecrafts(response.spacecrafts)

            override fun fetchFromLocal(): Flow<List<Spacecraft>> = spacecraftDao.getSpacecrafts()

            override suspend fun fetchFromRemote(): Response<SpacecraftList> = isroApi.getSpacecrafts()
        }.asFlow()
    }

    override suspend fun getLaunchers(): Flow<Resource<List<Launcher>>> {
        return object : NetworkBoundResource<List<Launcher>, LauncherList>() {

            override suspend fun saveRemoteData(response: LauncherList) = launcherDao.addLaunchers(response.launchers)

            override fun fetchFromLocal(): Flow<List<Launcher>> = launcherDao.getLaunchers()

            override suspend fun fetchFromRemote(): Response<LauncherList> = isroApi.getLaunchers()
        }.asFlow()
    }

    override suspend fun getCustomerSatellites(): Flow<Resource<List<CustomerSatellite>>> {
       return object : NetworkBoundResource<List<CustomerSatellite>,CustomerSatelliteList>(){

           override suspend fun saveRemoteData(response: CustomerSatelliteList) = satellitesDao.addSatellites(response.customerSatellites)

           override fun fetchFromLocal(): Flow<List<CustomerSatellite>> = satellitesDao.getSatellites()

           override suspend fun fetchFromRemote(): Response<CustomerSatelliteList> = isroApi.getCustomerSatellites()
       }.asFlow()
    }

    override suspend fun getCenters(): Flow<Resource<List<Centre>>> {
        return object : NetworkBoundResource<List<Centre>,CenterList>(){

            override suspend fun saveRemoteData(response: CenterList) = centresDao.addCentres(response.centres)

            override fun fetchFromLocal(): Flow<List<Centre>> = centresDao.getCentres()

            override suspend fun fetchFromRemote(): Response<CenterList> = isroApi.getCentres()
        }.asFlow()
    }



    /**
     * Function which returns $Resource type for state management(loading/error/success) from
     * retrofit api response
     * */
/*
    private fun <T : Any> responseToResource(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
*/


}