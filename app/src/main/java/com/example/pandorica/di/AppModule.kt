package com.example.pandorica.di

import com.example.pandorica.data.SecureStorage
import com.example.pandorica.data.TokenInMemoryStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTokenInMemoryStorage(secureStorage: SecureStorage): TokenInMemoryStorage = TokenInMemoryStorage(secureStorage)

}