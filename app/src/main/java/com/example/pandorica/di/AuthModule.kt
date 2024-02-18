package com.example.pandorica.di

import com.example.pandorica.data.Repository
import com.example.pandorica.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {
    @Binds
    @Singleton
    fun providesAuthRepository(authRepositoryImpl: RepositoryImpl): Repository
}
