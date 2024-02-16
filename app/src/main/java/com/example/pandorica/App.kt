package com.example.pandorica

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pandorica.data.Repository
import com.example.pandorica.data.RepositoryImpl
import com.example.pandorica.network.Api
import com.example.pandorica.network.RetrofitErrorHandler
import com.example.pandorica.ui.authorization.AuthorizationViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    private lateinit var retrofit: Retrofit
    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()

        retrofit = createRetrofit()
        val api = retrofit.create(Api::class.java)
        repository = RepositoryImpl(
            retrofitErrorHandler = RetrofitErrorHandler(),
            api = api
        )
    }


    private fun createRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("http://ec2-18-153-93-50.eu-central-1.compute.amazonaws.com:8081/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application =
                    checkNotNull(this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                AuthorizationViewModel((application as App).repository)
            }
        }
    }
}