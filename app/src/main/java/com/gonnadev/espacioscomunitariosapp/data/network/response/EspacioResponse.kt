package com.gonnadev.espacioscomunitariosapp.data.network.response

import com.gonnadev.espacioscomunitariosapp.domain.model.DiaModel
import com.gonnadev.espacioscomunitariosapp.domain.model.EspacioModel
import com.gonnadev.espacioscomunitariosapp.domain.model.HorarioModel
import com.gonnadev.espacioscomunitariosapp.domain.model.ReferenteModel
import com.gonnadev.espacioscomunitariosapp.domain.model.RetiroModel
import com.google.gson.annotations.SerializedName

data class EspacioResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("retiros") val retiros: List<RetiroResponse>,
    @SerializedName("tipo") val tipo: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("direccion") val direccion: String,
    @SerializedName("coordenadas") val coordenadas: String,
    @SerializedName("barrio") val barrio: String,
    @SerializedName("tarjeta") val tarjeta: Boolean,
    @SerializedName("asistencia") val asistencia: Int,
    @SerializedName("referente_fk") val referente: ReferenteResponse,
    @SerializedName("dia") val dia: List<DiaResponse>,
    @SerializedName("horario") val horario: List<HorarioResponse>
){
    fun toDomain(): EspacioModel {
        return EspacioModel(
            id = id,
            retiros = retiros.map { it.RetiroToDomain() },
            tipo = tipo,
            nombre = nombre,
            direccion = direccion,
            coordenadas = coordenadas,
            barrio = barrio,
            tarjeta = tarjeta,
            asistencia = asistencia,
            referente = referente.ReferenteToDomain(),
            dia = dia.map { it.DiaToDomain() },
            horario = horario.map { it.HorarioToDomain() }
        )
    }
}

data class RetiroResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("agente") val agente: String,
    @SerializedName("espacio_fk") val espacio: Int
) {
    fun RetiroToDomain(): RetiroModel {
        return RetiroModel(
            id = id,
            fecha = fecha,
            descripcion = descripcion,
            agente = agente,
            espacio = espacio
        )
    }
}

data class DiaResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("dia") val dia: String
) {
    fun DiaToDomain(): DiaModel {
        return DiaModel(
            id = id,
            dia = dia
        )
    }
}

data class HorarioResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("horario") val horario: String
) {
    fun HorarioToDomain(): HorarioModel {
        return HorarioModel(
            id = id,
            horario = horario
        )
    }
}

data class ReferenteResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apellido") val apellido: String,
    @SerializedName("telefono") val telefono: String,
    @SerializedName("dni") val dni: Int
){
    fun ReferenteToDomain(): ReferenteModel {
        return ReferenteModel(
            id = id,
            nombre = nombre,
            apellido = apellido,
            telefono = telefono,
            dni = dni
        )
    }
}