package com.gonnadev.espacioscomunitariosapp.domain.model

data class EspacioModel (
    val id: Int,
    val retiros: List<RetiroModel>,
    val tipo: String,
    val nombre: String,
    val direccion: String,
    val coordenadas: String,
    val barrio: String,
    val tarjeta: Boolean,
    val asistencia: Int,
    val referente: ReferenteModel,
    val dia: List<DiaModel>,
    val horario: List<HorarioModel>
)

data class RetiroModel (
    val id: Int,
    val fecha: String,
    val descripcion: String,
    val agente: String,
    val espacio: Int
)

data class DiaModel (
    val id: Int,
    val dia: String
)

data class HorarioModel (
    val id: Int,
    val horario: String
)

data class ReferenteModel (
    val id: Int,
    val nombre: String,
    val apellido: String,
    val telefono: String,
    val dni: Int
)