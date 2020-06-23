package com.example.clinicaunah.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicaunah.Utilidades.getValuePreferenceSeLogueo
import com.example.clinicaunah.R
import com.example.clinicaunah.Utilidades.getValuePreferenceIdUsuario
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()


        //Obtiene valor de preferencia (la primera ocasión es por default false).
        val seLogueo: Boolean = getValuePreferenceSeLogueo(applicationContext)

        if(seLogueo == false){

            var i: Intent = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    }

    private fun setupNavigation() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu -> {
                    Toast.makeText(this, "Este es de alberto", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.citas -> {
                    Toast.makeText(this, "Nadie aun", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.mensajeria -> {

                    // se llama a la activity de mensajeria
                    val intent = Intent(this, MensajeriaActivity::class.java )
                    startActivity(intent)

//                    Toast.makeText(this, "Este es de Brasly y Melvin", Toast.LENGTH_SHORT).show()

                    true
                }
                R.id.perfil -> {

                    // recupero el valor guardado en la preferencia
                    var id = getValuePreferenceIdUsuario(applicationContext)

                    Toast.makeText(this, id.toString(),Toast.LENGTH_LONG).show()

                    // se llama a la activity de perfil y le mando el id
                    val intent = Intent(this, PerfilActivity::class.java ).apply {
                        putExtra("id_usuario", id)
                    }
                    startActivity(intent)


                    true
                }
                else -> true
            }
        }
    }


}



