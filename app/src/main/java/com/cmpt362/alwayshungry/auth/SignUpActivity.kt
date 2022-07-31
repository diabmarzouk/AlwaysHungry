package com.cmpt362.alwayshungry.auth

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cmpt362.alwayshungry.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    // create Firebase authentication object
    private lateinit var authObj: FirebaseAuth

    //XML layout variables
    private lateinit var emailET: EditText
    private lateinit var passwordET: EditText
    private lateinit var signUpBtn : Button
    private lateinit var loginBtn: Button

    //Variables
    private lateinit var email : String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)
        emailET = findViewById(R.id.signUpEmail)
        passwordET = findViewById(R.id.signUpPassword)
        signUpBtn = findViewById(R.id.signUpContinueBtn)
        loginBtn = findViewById(R.id.signUpLoginBtn)

        //Initialize auth object
        authObj = Firebase.auth

        loginBtn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signUpBtn.setOnClickListener {
            email = emailET.text.toString()
            password = passwordET.text.toString()
            createAccount(email, password)

        }

        //... Here we create an activity that will be a screen that allows the user
        //      to sign up and we take this data, shove it into our Firebase using
        //      Firestore database...

    }

    public override fun onStart() {
        super.onStart()


        // No need to check if currUser is null because we already did this in MainActivity
//        val currUser = authObj.currentUser
//        if(currUser != null){

            // Reload function will reload the activity so that the user gets send to the login screen
//            reload()
//        }

    }

    private fun createAccount(email: String, password: String){
        authObj.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = authObj.currentUser

                   // updateUI is a function that might be implemented later
//                    updateUI(user)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
                }
            }
    }



}