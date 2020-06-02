package com.example.clinicaunah.API

import com.example.clinicaunah.Modelos.Paciente
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET("pacientes")
    fun getPacientes():Call<ArrayList<Paciente>>

    @GET("akita/images")
    fun getCharacterByName(): Call<Paciente>

//    @GET
//    fun getCharacterByName(@Url url:String): Call<Paciente>

}