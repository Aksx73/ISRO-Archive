package com.absut.isro.archive.domain.usecase

import com.absut.isro.archive.data.model.SpacecraftList
import com.absut.isro.archive.domain.repository.ISRORepository
import com.absut.newsapiclient.domain.util.Resource

class GetSpacecraftUseCase(private val isroRepository: ISRORepository) {

    suspend fun execute(): Resource<SpacecraftList> {
        return isroRepository.getSpacecrafts()
    }
}