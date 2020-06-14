package com.example.clinicaunah.Modelos

import com.google.gson.annotations.SerializedName

data class Login(@SerializedName("cuenta")val cuenta: String,
                 @SerializedName("password")val password: String) {
}