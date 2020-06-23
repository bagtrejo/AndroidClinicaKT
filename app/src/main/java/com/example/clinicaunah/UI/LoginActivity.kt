package com.example.clinicaunah.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicaunah.API.APIService
import com.example.clinicaunah.API.API_CLINICA_UNAH_URL
import com.example.clinicaunah.Utilidades.saveValuePreferenceSeLogueo
import com.example.clinicaunah.Modelos.Login
import com.example.clinicaunah.Modelos.Respuesta
import com.example.clinicaunah.R
import com.example.clinicaunah.Utilidades.id_usuario
import com.example.clinicaunah.Utilidades.saveValuePreferenceIdUsuario
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl(API_CLINICA_UNAH_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(APIService::class.java)

    fun login(view: View){

        val cuenta = cuentaInput.text.toString()
        val password = contraseñaInput.text.toString()


        service.loguear(Login(cuenta, password)).enqueue(object : Callback<Respuesta>{
            override fun onFailure(call: Call<Respuesta>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<Respuesta>,
                response: retrofit2.Response<Respuesta>
            ) {
                val respuesta = response.body() as Respuesta

                if(respuesta.codigoError == 0){

                    service.obtenerUsuario(respuesta).enqueue(object : Callback<JsonObject>{
                        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                        override fun onResponse(
                            call: Call<JsonObject>,
                            response: Response<JsonObject>
                        ) {

                            saveValuePreferenceSeLogueo(applicationContext, true)
                            val datos: JsonObject? = response.body()

                            saveValuePreferenceIdUsuario(applicationContext, datos!!.get("id").asInt)

                            Log.d("datosUsuario", datos.toString())

                            finish()
                        }

                    })



                }else{

                    cuentaInput.setText("")
                    contraseñaInput.setText("")

                    Toast.makeText(this@LoginActivity, respuesta.msg, Toast.LENGTH_LONG).show()
                }

            }

        })



    }
}
