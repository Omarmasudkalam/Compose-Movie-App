package com.omk.composemovie.di.modules

import com.omk.composemovie.data.remote.ApiService
import com.omk.composemovie.data.repository.AppRepository
import com.omk.composemovie.data.repository.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Singleton

@DelicateCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService): AppRepository {
        return AppRepositoryImpl(apiService)
    }
}