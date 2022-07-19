package com.cmpt362.alwayshungry

import android.content.Intent
import android.os.Bundle
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity

class userInfo : AppCompatActivity() {

    private lateinit var nextBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_information)

        nextBtn = findViewById(R.id.nextBtn)

        nextBtn.setOnClickListener{
            val intent = Intent(this, welcomePreferences::class.java)
            startActivity(intent)
        }

    }

}