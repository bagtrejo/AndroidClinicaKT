package com.example.clinicaunah.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.clinicaunah.API.APIService
import com.example.clinicaunah.API.API_CLINICA_UNAH_URL
import com.example.clinicaunah.Modelos.Login
import com.example.clinicaunah.Modelos.Paciente
import com.example.clinicaunah.Modelos.Respuesta
import com.example.clinicaunah.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response as Response1

class Login : AppCompatActivity() {

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

                    service.obtenerUsuario(respuesta).enqueue(object : Callback<Any>{
                        override fun onFailure(call: Call<Any>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                        override fun onResponse(
                            call: Call<Any>,
                            response: retrofit2.Response<Any>
                        ) {
                            val datos = response.body()

                            Log.d("datosUsuario", datos.toString())


//                            val intent = Intent(this@Login, MainActivity::class.java)
//                            startActivity(intent)
                        }


                    })



                }else{

                    cuentaInput.setText("")
                    contraseñaInput.setText("")

                    Toast.makeText(this@Login, respuesta.msg, Toast.LENGTH_LONG).show()
                }

            }

        })



    }
}
