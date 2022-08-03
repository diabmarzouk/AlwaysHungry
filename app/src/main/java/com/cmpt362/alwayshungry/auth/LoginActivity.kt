package com.cmpt362.alwayshungry.auth

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cmpt362.alwayshungry.R
import com.cmpt362.alwayshungry.smartFridgeManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    // create Firebase authentication object
    private lateinit var authObj: FirebaseAuth

    //XML layout variables
    private lateinit var emailET: EditText
    private lateinit var passwordET: EditText
    private lateinit var signUpBtn : Button
    private lateinit var loginBtn: Button

    // Variables
    private lateinit var email : String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        emailET = findViewById(R.id.loginEmail)
        passwordET = findViewById(R.id.loginPassword)
        signUpBtn = findViewById(R.id.loginSignUpBtn)
        loginBtn = findViewById(R.id.loginLoginBtn)

        loginBtn.setOnClickListener{
            email = emailET.text.toString()
            password = passwordET.text.toString()
            signIn(email, password)

        }

        signUpBtn.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        //Initialize auth object
        authObj = Firebase.auth

    }

    private fun signIn(email: String, password: String){
        authObj.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    if(authObj.currentUser!!.isEmailVerified){
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(ContentValues.TAG, "signInWithEmail:success")

                        val intent = Intent(this, smartFridgeManager::class.java)
                        startActivity(intent)

                    }else{
                        Toast.makeText(baseContext, "Your email has not been verified. Please check your email.",
                            Toast.LENGTH_SHORT).show()
                    }

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Your email and password combination is incorrect.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }


}