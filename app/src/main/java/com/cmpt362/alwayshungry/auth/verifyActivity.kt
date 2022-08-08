package com.cmpt362.alwayshungry.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cmpt362.alwayshungry.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class verifyActivity : AppCompatActivity() {

    private lateinit var loginBtn: Button
    private lateinit var resendBtn: Button

    private val user = Firebase.auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.verify_account_activity)

        loginBtn = findViewById(R.id.verifyLoginBtn)
        resendBtn = findViewById(R.id.verifyResendBtn)

        resendBtn.setOnClickListener {
            if(user?.isEmailVerified == false){
                user?.sendEmailVerification()
            }else{
                Toast.makeText(baseContext, "Your account has already been verified!",
                    Toast.LENGTH_LONG).show()
            }
        }

        loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }



    }

}