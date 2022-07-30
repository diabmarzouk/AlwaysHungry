package com.cmpt362.alwayshungry.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //Initialize auth object
        auth = Firebase.auth

        //... Here we create an activity that will show when the user needs to login
        //      to the app. If they know they already have an account, they can try
        //      to login by inserting their email and password.

        //************ SUGGESTION ***************
        //  We can probably get away with just using "Login with Google" and avoid
        //      having to store data on our own. Will look further into it.

    }

}