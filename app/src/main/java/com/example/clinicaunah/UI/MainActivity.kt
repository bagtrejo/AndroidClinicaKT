package com.example.clinicaunah.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.clinicaunah.API.APIService
import com.example.clinicaunah.API.API_CLINICA_UNAH_URL
import com.example.clinicaunah.Modelos.Paciente
import com.example.clinicaunah.R
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(API_CLINICA_UNAH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val service = retrofit.create(APIService::class.java)




    fun mostrarDatos(view: View){

        service.getPacientes().enqueue(object : Callback<ArrayList<Paciente>> {

            override fun onResponse(
                call: Call<ArrayList<Paciente>>,
                response: retrofit2.Response<ArrayList<Paciente>>
            ) {

                val pacientes = response.body()

                Log.d("datos de la api", pacientes?.get(0).toString())


            }

            override fun onFailure(call: Call<ArrayList<Paciente>>, t: Throwable) {
                t.printStackTrace()
            }


        })


    }



}



