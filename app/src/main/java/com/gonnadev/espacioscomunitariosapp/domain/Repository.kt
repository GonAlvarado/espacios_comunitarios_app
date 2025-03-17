package com.gonnadev.espacioscomunitariosapp.domain

import com.gonnadev.espacioscomunitariosapp.domain.model.EspacioModel
import com.gonnadev.espacioscomunitariosapp.domain.model.LoginModel

interface Repository {
    suspend fun login(username: String, password: String): LoginModel?

    suspend fun getList(): List<EspacioModel>

    suspend fun getDetail(id: Int): EspacioModel?
}