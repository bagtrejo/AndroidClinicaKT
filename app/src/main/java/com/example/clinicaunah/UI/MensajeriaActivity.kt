package com.example.clinicaunah.UI

import android.content.Intent
import android.content.Intent.createChooser
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clinicaunah.Adaptadores.ChatAdapter
import com.example.clinicaunah.Modelos.Mensaje
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.clinicaunah.R
import com.google.firebase.FirebaseApp
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_mensajeria.*


class MensajeriaActivity : AppCompatActivity() {

//    private var adapter: FirebaseListAdapter<ChatMessage>? = null
//    private var adapter: FirebaseRecyclerAdapter<ChatMessage, Holder>? = null

    lateinit var adapter: ChatAdapter
    val SIGN_IN_REQUEST_CODE = 1
    val PHOTO_SEND_REQUEST_CODE = 2
    val PHOTO_PERFIL_REQUEST_CODE = 3
    var fotoPerfilCadena: String = ""

    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var databaseReference: DatabaseReference = database.getReference("Chat")// sala de chat nombre
    var storage: FirebaseStorage = FirebaseStorage.getInstance()
    lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensajeria)


        FirebaseApp.initializeApp(this);



        adapter = ChatAdapter(this)
        val l : LinearLayoutManager = LinearLayoutManager(this)
        list_of_messages.layoutManager = l
        list_of_messages.adapter = adapter


        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                setScrollBar()
            }
        })

//        if(FirebaseAuth.getInstance().currentUser == null) {
//            // Start sign in/sign up activity
//            startActivityForResult(
//                AuthUI.getInstance()
//                    .createSignInIntentBuilder()
//                    .build(),
//                SIGN_IN_REQUEST_CODE
//            )
//
//        } else {
//
//            // Load chat room contents
//
//            // User is already signed in. Therefore, display
//            // a welcome Toast
//            Toast.makeText(this,
//                "Welcome " + FirebaseAuth.getInstance()
//                    .currentUser!!.displayName,
//                Toast.LENGTH_LONG)
//                .show()
//
//
//        }


        fab.setOnClickListener(View.OnClickListener {

            var mensajeEnviar = input.text.toString()

            if(!mensajeEnviar.isEmpty()){

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                databaseReference
                    .push()
                    .setValue(
                        Mensaje(

                            messageText = mensajeEnviar,
                            messageUser = "Brasly",
                            contieneFoto = false

//                            keyEmisor = FirebaseAuth.getInstance().uid!!

                        )
                    )

                // Clear the input
                input.setText("")

            }





        })

        btnFoto.setOnClickListener(View.OnClickListener{

            val i: Intent = Intent(Intent.ACTION_GET_CONTENT)
            i.type = "image/jpeg"
            i.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            startActivityForResult(createChooser(i, "Selecciona una foto"), PHOTO_SEND_REQUEST_CODE)
        })


        fotoPerfil.setOnClickListener(View.OnClickListener {

            val i: Intent = Intent(Intent.ACTION_GET_CONTENT).apply {

                type = "image/jpeg"
                putExtra(Intent.EXTRA_LOCAL_ONLY, true)
                startActivityForResult(createChooser(this, "Selecciona una foto"), PHOTO_PERFIL_REQUEST_CODE)

            }

        })

        databaseReference.addChildEventListener(object: ChildEventListener{

            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {

                val m : Mensaje = dataSnapshot.getValue(Mensaje::class.java)!!
                adapter.addMensaje(m)
            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }
        })


    }

    private fun setScrollBar(){

        list_of_messages.scrollToPosition(adapter.itemCount - 1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == SIGN_IN_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this,
                    "Successfully signed in. Welcome!",
                    Toast.LENGTH_LONG)
                    .show();
            } else {
                Toast.makeText(this,
                    "We couldn't sign you in. Please try again later.",
                    Toast.LENGTH_LONG)
                    .show();

                // Close the app
                finish();
            }


        }

        if(requestCode == PHOTO_SEND_REQUEST_CODE && resultCode == RESULT_OK){


            var u : Uri? = data?.data
            storageReference = storage.getReference("imagenes_chat")//nombre de la caperta donde se guardan las imagenes en firebase
            val fotoReferencia: StorageReference = storageReference.child(u?.lastPathSegment!!)

            fotoReferencia.putFile(u).addOnSuccessListener {

                fotoReferencia.downloadUrl.addOnSuccessListener {

                    val m: Mensaje = Mensaje(
                        messageText = "Alguien ha enviado una imagen",
                        messageUser = "Alguien",
                        contieneFoto = true,
//                        keyEmisor = FirebaseAuth.getInstance().uid!!,
                        urlFoto = it.toString())
                    databaseReference.push().setValue(m)

                }


            }


        }else  if(requestCode == PHOTO_PERFIL_REQUEST_CODE && resultCode == RESULT_OK){

            var u : Uri? = data?.data
            storageReference = storage.getReference("fotos_perfil")//nombre de la caperta donde se guardan las imagenes en firebase
            val fotoReferencia: StorageReference = storageReference.child(u?.lastPathSegment!!)

            fotoReferencia.putFile(u).addOnSuccessListener {

                fotoReferencia.putFile(u).addOnSuccessListener {

                    fotoReferencia.downloadUrl.addOnSuccessListener {

                        fotoPerfilCadena = it.toString()
                        val m: Mensaje = Mensaje(
                            messageText = "Alguien ha cambiado su foto de perfil",
                            messageUser = "Alguien",
                            contieneFoto = true,
//                            foto_perfil = fotoPerfilCadena,
                            urlFoto = fotoPerfilCadena
//                            keyEmisor = FirebaseAuth.getInstance().uid!!
                        )
                        databaseReference.push().setValue(m)
                        Glide.with(this).load(it.toString()).into(fotoPerfil)

                    }


                }

            }
        }
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }


//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle item selection
//
//        if(item.itemId == R.id.menu_sign_out) {
//            AuthUI.getInstance().signOut(this)
//                .addOnCompleteListener(OnCompleteListener<Void>() {
//
//                    Toast.makeText(this,
//                        "You have been signed out.",
//                        Toast.LENGTH_LONG)
//                        .show();
//
//                    // Close activity
//                    finish();
//
//                });
//        }
//        return true;
//    }

}
