package com.gonnadev.espacioscomunitariosapp.data.network

import com.gonnadev.espacioscomunitariosapp.data.network.request.LoginRequest
import com.gonnadev.espacioscomunitariosapp.data.network.response.EspacioResponse
import com.gonnadev.espacioscomunitariosapp.data.network.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("login/")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("espacios/")
    suspend fun getList(): List<EspacioResponse>

    @GET("espacios/{id}")
    suspend fun getDetail(@Path("id") id: Int): EspacioResponse

}