package com.example.pandorica.di

import com.example.pandorica.network.Api
import com.example.pandorica.network.RetrofitErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIMEOUT_IN_SEC = 30L

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofitErrorHandler() = RetrofitErrorHandler()


    @Singleton
    @Provides
    fun providesOkHttp(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .dispatcher(Dispatcher().apply { maxRequestsPerHost = 20 })
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun providesRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://ec2-18-153-93-50.eu-central-1.compute.amazonaws.com:8081/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providesConsoleHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()


    @Singleton
    @Provides
    fun providesMobileAuthApi(retrofit: Retrofit): Api =
        retrofit.create(Api::class.java)
}