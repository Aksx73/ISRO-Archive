package com.absut.isro.archive.domain.usecase

import com.absut.isro.archive.data.model.Spacecraft
import com.absut.isro.archive.data.model.SpacecraftList
import com.absut.isro.archive.domain.repository.ISRORepository
import com.absut.isro.archive.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetSpacecraftUseCase(private val isroRepository: ISRORepository) {

    suspend fun execute(): Flow<Resource<List<Spacecraft>>> {
        return isroRepository.getSpacecrafts()
    }
}