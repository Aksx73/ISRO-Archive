package com.absut.isro.archive.domain.usecase

import com.absut.isro.archive.data.model.LauncherList
import com.absut.isro.archive.domain.repository.ISRORepository
import com.absut.newsapiclient.domain.util.Resource

class GetLaunchersUseCase(private val isroRepository: ISRORepository)  {

    suspend fun execute():Resource<LauncherList>{
        return isroRepository.getLaunchers()
    }
}