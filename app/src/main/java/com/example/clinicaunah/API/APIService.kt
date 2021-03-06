package com.example.clinicaunah.API

import com.example.clinicaunah.Modelos.Login
import com.example.clinicaunah.Modelos.Paciente
import com.example.clinicaunah.Modelos.Respuesta
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @GET("pacientes")
    fun getPacientes():Call<ArrayList<Paciente>>

    @POST("obtenerUsuario")
    fun loguear(@Body login: Login): Call<Respuesta>

    @POST("getCurrentUser")
    fun obtenerUsuario(@Body token: Respuesta): Call<JsonObject>

    @GET("pacientes/{id}")
    fun obtenerPaciente(@Path("id") id: Int):Call<Paciente>

//    @GET
//    fun getCharacterByName(@Url url:String): Call<Paciente>

}