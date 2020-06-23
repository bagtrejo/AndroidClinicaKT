package com.example.clinicaunah.Utilidades

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


var id_usuario : Int = 0

fun saveValuePreferenceSeLogueo(
    context: Context,
    seLogueo: Boolean?
) {
    val settings =
        context.getSharedPreferences("seLogueo", MODE_PRIVATE)
    val editor: SharedPreferences.Editor
    editor = settings.edit()
    editor.putBoolean("seLogueo", seLogueo!!)
    editor.commit()
}


fun getValuePreferenceSeLogueo(context: Context): Boolean {
    val preferences =
        context.getSharedPreferences("seLogueo", MODE_PRIVATE)
    return preferences.getBoolean("seLogueo", false)
}

fun saveValuePreferenceIdUsuario(
    context: Context,
    id: Int?
) {
    val settings =
        context.getSharedPreferences("id_usuario", MODE_PRIVATE)
    val editor: SharedPreferences.Editor
    editor = settings.edit()
    editor.putInt("id_usuario", id!!)
    editor.commit()
}


fun getValuePreferenceIdUsuario(context: Context): Int {
    val preferences =
        context.getSharedPreferences("id_usuario", MODE_PRIVATE)
    return preferences.getInt("id_usuario", 0)
}
