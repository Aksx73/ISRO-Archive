package com.absut.isro.archive.ui.di

import android.os.Build
import com.absut.isro.archive.BuildConfig

import com.absut.isro.archive.data.api.ISROApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesISROApiService(retrofit: Retrofit):ISROApiService{
        return retrofit.create(ISROApiService::class.java)
    }

}