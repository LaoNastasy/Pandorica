package com.example.pandorica.network

import android.util.Log
import com.example.pandorica.domain.RefreshTokenUseCase
import dagger.Lazy
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

private const val AUTHORIZATION_HEADER = "Authorization"
private const val AUTHORIZATION_PREFIX = "Bearer"

class BearerAuthenticator @Inject constructor(
    private val refreshTokenUseCase: Lazy<RefreshTokenUseCase>,
) : Authenticator {
    private val mutex = Mutex()

    override fun authenticate(route: Route?, response: Response): Request? =
        with(response.request) {
            if (url.pathSegments.contains("auth")) {
                return null
            }

            val auth = runBlocking {
                try {
                    refreshTokenUseCase.get().invoke()
                } catch (e: Exception) {
                    Log.e("BearerAuthenticator", e.stackTraceToString())
                    null
                }
            } ?: return null

            return newBuilder()
                .header(AUTHORIZATION_HEADER, "$AUTHORIZATION_PREFIX ${auth.accessToken}")
                .build()
        }
}
