package com.citiustech.data.di

import com.citiustech.data.repository.RepositoryImpl
import com.citiustech.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideRepository(repository: RepositoryImpl): Repository
}