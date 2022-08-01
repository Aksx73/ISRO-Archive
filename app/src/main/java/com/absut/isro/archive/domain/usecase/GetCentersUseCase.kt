package com.absut.isro.archive.domain.usecase

import com.absut.isro.archive.data.model.CenterList
import com.absut.isro.archive.domain.repository.ISRORepository
import com.absut.newsapiclient.domain.util.Resource
import retrofit2.Response

class GetCentersUseCase(private val isroRepository: ISRORepository)  {

    suspend fun execute(): Resource<CenterList>{
        return isroRepository.getCenters()
    }

}