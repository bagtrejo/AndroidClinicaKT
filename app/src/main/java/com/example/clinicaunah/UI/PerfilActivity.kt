package com.example.clinicaunah.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.clinicaunah.API.APIService
import com.example.clinicaunah.API.API_CLINICA_UNAH_URL
import com.example.clinicaunah.Modelos.Paciente
import com.example.clinicaunah.R
import kotlinx.android.synthetic.main.activity_perfil.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PerfilActivity : AppCompatActivity() {

    var ID_USUARIO: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        intent.let {
            ID_USUARIO = it.getIntExtra("id_usuario", ID_USUARIO)
            mostrarDatos(ID_USUARIO)
        }

    }


    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(API_CLINICA_UNAH_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(APIService::class.java)




    fun mostrarDatos(id_usuario: Int){

        service.obtenerPaciente(id_usuario).enqueue(object : Callback<Paciente> {

            override fun onResponse(
                call: Call<Paciente>,
                response: retrofit2.Response<Paciente>
            ) {

                val paciente = response.body()

                Log.d("datos de la api", paciente?.toString())

                nombre.text = paciente?.nombre_completo

                numeroCuenta.text = "Numero de cuenta: ${paciente?.numero_cuenta.toString()}"

                numeroIdentidad.text = "Numero de identidad: ${paciente?.numero_identidad.toString()}"

                edad.text = "Edad: ${paciente?.edad.toString()}"

                sexo.text = "Sexo: ${paciente?.sexo.toString()}"

                carrera.text = "Carrera: ${paciente?.carrera.toString()}"

                Glide.with(this@PerfilActivity).load(paciente?.imagen).error(R.drawable.descarga).into(imagenPaciente)

            }

            override fun onFailure(call: Call<Paciente>, t: Throwable) {
                t.printStackTrace()
            }


        })


    }

}
