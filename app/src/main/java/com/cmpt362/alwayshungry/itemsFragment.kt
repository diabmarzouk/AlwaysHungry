package com.cmpt362.alwayshungry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth


import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class itemsFragment : Fragment() {

    private lateinit var authObj: FirebaseAuth

//    private lateinit var database: DatabaseReference



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root= inflater.inflate(R.layout.items_fragment, container, false)

//        database = Firebase.database.reference

        authObj = Firebase.auth
        val db = Firebase.firestore
        val user = authObj.currentUser

        println("debug: Hello items")

        db.collection("users").document(user!!.uid).collection("items")
//

//        database.child("users").child(user!!.uid).child("items").get().addOnSuccessListener {
//            //do something
//            println("debug: firebase Got value ${it.value}")
//        }.addOnFailureListener{
//            //do something else
//            println("debug: sorry didn't work $it")
//
//        }




        return root
    }
}