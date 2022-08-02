package com.absut.isro.archive.domain.usecase

import com.absut.isro.archive.data.remote.model.CenterList
import com.absut.isro.archive.data.remote.model.Centre
import com.absut.isro.archive.domain.repository.ISRORepository
import com.absut.isro.archive.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class GetCentersUseCase(private val isroRepository: ISRORepository) {

    suspend fun execute(): Resource<CenterList> {
        return isroRepository.getCenters()
    }

}