package com.example.clinicaunah.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.clinicaunah.API.APIService
import com.example.clinicaunah.API.API_CLINICA_UNAH_URL
import com.example.clinicaunah.Modelos.Paciente
import com.example.clinicaunah.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Perfil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
    }

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(API_CLINICA_UNAH_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(APIService::class.java)




    fun mostrarDatos(view: View){

        service.obtenerPaciente(id = 1).enqueue(object : Callback<Paciente> {

            override fun onResponse(
                call: Call<Paciente>,
                response: retrofit2.Response<Paciente>
            ) {

                val pacientes = response.body()

                Log.d("datos de la api", pacientes?.toString())


            }

            override fun onFailure(call: Call<Paciente>, t: Throwable) {
                t.printStackTrace()
            }


        })


    }
}
