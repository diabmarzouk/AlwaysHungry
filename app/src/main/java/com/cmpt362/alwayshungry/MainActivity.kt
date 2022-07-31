package com.cmpt362.alwayshungry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Button
import android.widget.TextView
import com.cmpt362.alwayshungry.auth.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var startBtn : Button
    private lateinit var authObj: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_screen)
        startBtn = findViewById(R.id.startBtn)

        authObj = Firebase.auth

        val currUser = authObj.currentUser

        // If there is no user already logged in from the apps memory...
        if(currUser == null){

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

            // Launch intent to launch SignUpActivity
            //  This activity will have a sign UP screen to begin with and if
            //      the user already has an account, they can click a button that will
            //      launch another intent to launc the LoginActivity



        }else{
            // Launch intent to go straight to the fragment tab page
            val intent = Intent(this, smartFridgeManager::class.java)
            startActivity(intent)
        }

        startBtn.setOnClickListener{
            val intent = Intent(this, userInfo::class.java )
            startActivity(intent)
        }
    }

}