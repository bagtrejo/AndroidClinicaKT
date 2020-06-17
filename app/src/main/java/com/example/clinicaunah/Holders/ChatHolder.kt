package com.example.clinicaunah.Holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicaunah.R
import de.hdodenhof.circleimageview.CircleImageView

class ChatHolder(messageItemView: View): RecyclerView.ViewHolder(messageItemView) {

    var message_user: TextView = messageItemView.findViewById(R.id.message_user)
    var message_text: TextView = messageItemView.findViewById(R.id.message_text)
    val message_time: TextView = messageItemView.findViewById(R.id.message_time)
    val mensajeFoto: ImageView = messageItemView.findViewById(R.id.mensajeFoto)
    val fotoPerfilMensaje: CircleImageView = messageItemView.findViewById(R.id.fotoPerfilMensaje)

}