package com.example.clinicaunah.Modelos

import com.google.gson.annotations.SerializedName

data class Respuesta(@SerializedName("codigoError")val codigoError: Int,
                     @SerializedName("msg")val msg: String,
                     @SerializedName("token")val token: String){
}