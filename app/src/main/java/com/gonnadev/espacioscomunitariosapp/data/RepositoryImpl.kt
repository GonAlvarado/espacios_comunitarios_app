package com.gonnadev.espacioscomunitariosapp.data

import android.util.Log
import com.gonnadev.espacioscomunitariosapp.data.network.ApiService
import com.gonnadev.espacioscomunitariosapp.data.network.request.LoginRequest
import com.gonnadev.espacioscomunitariosapp.domain.Repository
import com.gonnadev.espacioscomunitariosapp.domain.model.EspacioModel
import com.gonnadev.espacioscomunitariosapp.domain.model.LoginModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {
    override suspend fun login(username: String, password: String) : LoginModel? {
        runCatching { apiService.login(LoginRequest(username, password)) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("Error", "Ha ocurrido un error ${it.message}") }
        return null
    }

    override suspend fun getList(): List<EspacioModel> {
        runCatching { apiService.getList() }
            .onSuccess { return it.map { it.toDomain() } }
            .onFailure { Log.i("Error", "Ha ocurrido un error ${it.message}") }
        return emptyList()
    }

    override suspend fun getDetail(id: Int): EspacioModel? {
        runCatching { apiService.getDetail(id) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("Error", "Ha ocurrido un error ${it.message}") }
        return null    }
}