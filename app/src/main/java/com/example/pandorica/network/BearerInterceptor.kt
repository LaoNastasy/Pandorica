package com.example.pandorica.network

import com.example.pandorica.domain.GetTokenUseCase
import okhttp3.Interceptor
import javax.inject.Inject

private const val AUTHORIZATION_HEADER = "Authorization"
private const val AUTHORIZATION_PREFIX = "Bearer"

class BearerInterceptor @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.proceed(
        chain.request().newBuilder()
            .also { builder ->
                getTokenUseCase.invoke()?.let {
                    builder.addHeader(AUTHORIZATION_HEADER, "$AUTHORIZATION_PREFIX $it")
                }
            }
            .build()
    )
}
