package com.example.clinicaunah.Adaptadores

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.clinicaunah.Holders.ChatHolder
import com.example.clinicaunah.Modelos.Mensaje
import com.example.clinicaunah.R

class ChatAdapter(val context: Context): RecyclerView.Adapter<ChatHolder>() {


    private var listMensaje: ArrayList<Mensaje> = ArrayList()

    fun addMensaje(m : Mensaje){

        listMensaje.add(m)
        notifyItemInserted(listMensaje.size)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {

        if (viewType == 1){
           val  v = LayoutInflater.from(context).inflate(R.layout.message_receptor, parent, false)
            return ChatHolder(v)
        }else{
           val  v = LayoutInflater.from(context).inflate(R.layout.message_receptor, parent, false)
            return ChatHolder(v)
        }


    }

    override fun getItemCount(): Int {
        return listMensaje.size
    }

    //este siguiente metodo es para el estilo de los chats
    override fun getItemViewType(position: Int): Int {
        if (listMensaje[position].messageUser != null){
            if(listMensaje[position].messageUser == "Brasly"){
                return 1
            }else{
                return -1
            }
        }else{
            return -1
        }

        //return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {

        holder.message_text.text = listMensaje[position].messageText
        holder.message_user.text = listMensaje[position].messageUser

        holder.message_time.text =
            DateFormat.format("dd-MM-yyyy (HH:mm:ss)", listMensaje[position].messageTime)

        if (listMensaje[position].contieneFoto){


            holder.mensajeFoto.visibility = View.VISIBLE
            holder.message_text.visibility = View.VISIBLE

            val options: RequestOptions = RequestOptions()
                .fitCenter()
                .error(R.drawable.ic_launcher_foreground)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)

            Glide
                .with(context)
                .load(listMensaje[position].urlFoto)
                .apply(options)
                .into(holder.mensajeFoto)
//            Picasso.get().load(listMensaje[position].urlFoto).error(R.drawable.ic_launcher_foreground).into(holder.mensajeFoto);
//            Glide.with(context).load(listMensaje[position].urlFoto).into(holder.mensajeFoto)

        }else{


            holder.mensajeFoto.visibility = View.GONE
            holder.message_text.visibility = View.VISIBLE

        }

//        if(listMensaje[position].usuario.fotoPerfilUrl?.isEmpty()!!){
//
//            holder.fotoPerfilMensaje.setImageResource(R.mipmap.ic_launcher)
//
//        }else{
//
//            Glide.with(context).load(listMensaje[position].usuario.fotoPerfilUrl).into(holder.fotoPerfilMensaje)
//
//        }
    }

}