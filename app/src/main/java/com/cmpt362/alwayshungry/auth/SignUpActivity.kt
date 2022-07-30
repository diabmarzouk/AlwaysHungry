package com.cmpt362.alwayshungry.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize auth object
        auth = Firebase.auth

        //... Here we create an activity that will be a screen that allows the user
        //      to sign up and we take this data, shove it into our Firebase using
        //      Firestore database...

    }

}