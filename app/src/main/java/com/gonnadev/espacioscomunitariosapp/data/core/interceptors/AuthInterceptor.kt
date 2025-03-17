package com.gonnadev.espacioscomunitariosapp.data.core.interceptors

import com.gonnadev.espacioscomunitariosapp.data.core.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { tokenManager.getToken() }
        val request =
            chain.request().newBuilder().header("Authorization", "Token $token").build()
        return chain.proceed(request)
    }
}