package com.example.clinicaunah.Modelos

import android.net.Uri
import java.util.*

open class Mensaje(
    var messageText: String? = "",
    var messageUser: String?= "",
    var urlFoto: String? = "",
    var contieneFoto: Boolean = false,
//    val keyEmisor: String,
//    var tipo_mensaje: String= "",
//    var foto_perfil: String= "",
    var messageTime: Long = Date().time
){


}