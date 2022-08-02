package com.absut.isro.archive.di

import com.absut.isro.archive.data.remote.ISROApi
import com.absut.isro.archive.data.repository.ISRORepositoryImpl
import com.absut.isro.archive.domain.repository.ISRORepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesIsroRepository(isroApi: ISROApi): ISRORepository {
        return ISRORepositoryImpl(isroApi)
    }


}