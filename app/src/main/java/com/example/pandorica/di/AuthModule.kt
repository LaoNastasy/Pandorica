package com.example.pandorica.di

import com.example.pandorica.data.Repository
import com.example.pandorica.data.RepositoryImpl
import com.example.pandorica.data.TokenRepository
import com.example.pandorica.data.TokenRepositoryImpl
import com.example.pandorica.network.BearerAuthenticator
import com.example.pandorica.network.BearerInterceptor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Authenticator
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {
    @Binds
    @Singleton
    fun providesAuthRepository(authRepositoryImpl: RepositoryImpl): Repository

    @Binds
    @IntoSet
    fun providesBearerInterceptor(bearerInterceptor: BearerInterceptor): Interceptor


    @Binds
    @Singleton
    fun providesTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository

    @Binds
    @IntoSet
    fun providesBearerAuthenticator(bearerAuthenticator: BearerAuthenticator): Authenticator
}
