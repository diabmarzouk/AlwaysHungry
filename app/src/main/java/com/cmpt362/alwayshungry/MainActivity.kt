package com.cmpt362.alwayshungry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var startBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_screen)
        startBtn = findViewById(R.id.startBtn)


        startBtn.setOnClickListener{
            val intent = Intent(this, userInfo::class.java )
            startActivity(intent)
        }
    }

}