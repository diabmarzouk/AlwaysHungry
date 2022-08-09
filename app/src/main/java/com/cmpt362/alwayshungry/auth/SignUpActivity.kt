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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    // create Firebase authentication object
    private lateinit var authObj: FirebaseAuth

    //XML layout variables
    private lateinit var emailET: EditText
    private lateinit var passwordET: EditText
    private lateinit var confirmPassET: EditText
    private lateinit var displayNameET: EditText
    private lateinit var signUpBtn : Button
    private lateinit var loginBtn: Button

    //Variables
    private lateinit var email : String
    private lateinit var password: String
    private lateinit var conPassword: String
    private lateinit var displayName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)
        emailET = findViewById(R.id.signUpEmail)
        passwordET = findViewById(R.id.signUpPassword)
        confirmPassET = findViewById(R.id.signUpPasswordConfirm)
        displayNameET = findViewById(R.id.signUpNameET)
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
            conPassword = confirmPassET.text.toString()
            displayName = displayNameET.text.toString()
            if(password != conPassword){
                Toast.makeText(baseContext, "Passwords do not match.",
                    Toast.LENGTH_LONG).show()
            }else{
                createAccount(email, password, displayName)
            }


        }

        //... Here we create an activity that will be a screen that allows the user
        //      to sign up and we take this data, shove it into our Firebase using
        //      Firestore database...

    }

    public override fun onStart() {
        super.onStart()
    }

    private fun createAccount(email: String, password: String, name: String){

        val db = Firebase.firestore

        authObj.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    // Sign in success, update UI with the signed-in user's information
                    authObj.currentUser!!.sendEmailVerification().addOnCompleteListener { task ->
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = authObj.currentUser
                        if (user != null) {

                            val intent = Intent(this, verifyActivity::class.java)

                            db.collection("users").document(user.uid)
                                .collection("recipes").document("initalizer").set("" to "")

                            db.collection("users").document(user.uid)
                                .collection("items").document("meat")
                                .set("" to "")

                            db.collection("users").document(user.uid)
                                .collection("items").document("vegetables")
                                .set("" to "")

                            db.collection("users").document(user.uid)
                                .collection("items").document("dairy")
                                .set("" to "")

                            db.collection("users").document(user.uid)
                                .collection("items").document("other")
                                .set("" to "")

                            // Change this intent to launch an activity that asks you to verify your account.
                            // It will contain a re-send button and a button to return to the sign up page

                            startActivity(intent)
                        }

                    }

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_LONG).show()
                }
            }
    }



}