package com.absut.isro.archive.domain.usecase

import com.absut.isro.archive.data.model.Launcher
import com.absut.isro.archive.data.model.LauncherList
import com.absut.isro.archive.data.model.Spacecraft
import com.absut.isro.archive.domain.repository.ISRORepository
import com.absut.isro.archive.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetLaunchersUseCase(private val isroRepository: ISRORepository)  {

    suspend fun execute(): Flow<Resource<List<Launcher>>> {
        return isroRepository.getLaunchers()
    }
}