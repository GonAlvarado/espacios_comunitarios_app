package com.gonnadev.espacioscomunitariosapp.data.network.response

import com.gonnadev.espacioscomunitariosapp.domain.model.LoginModel
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") val token: String,
    @SerializedName("user") val user: Int
){
    fun toDomain(): LoginModel {
        return LoginModel(
            token = token,
            user = user
        )
    }
}