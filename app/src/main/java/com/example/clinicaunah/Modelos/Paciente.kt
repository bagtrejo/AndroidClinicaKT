package com.example.clinicaunah.Modelos

import com.google.gson.annotations.SerializedName

data class Paciente(

    @SerializedName("id_paciente") val id_paciente: Int,
    @SerializedName("nombre_completo")var nombre_completo: String,
    @SerializedName("numero_cuenta")val numero_cuenta: String,
    @SerializedName("numero_identidad")val numero_identidad: String,
    @SerializedName("imagen")var imagen: String,
    @SerializedName("direccion")var direccion: String,
    @SerializedName("carrera")var carrera: String,
    @SerializedName("lugar_procedencia")var lugar_procedencia: String,
    @SerializedName("fecha_nacimiento")val fecha_nacimiento: String,
    @SerializedName("sexo")val sexo: String,
    @SerializedName("estado_civil")var estado_civil: String,
    @SerializedName("seguro_medico")var seguro_medico: String,
    @SerializedName("categoria")var categoria: String,
    @SerializedName("prosene")var prosene: String,
    @SerializedName("edad")var edad: String

) {


}