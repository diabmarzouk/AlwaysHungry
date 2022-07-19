package com.cmpt362.alwayshungry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class welcomePreferences : AppCompatActivity() {

    private lateinit var backBtn : Button
    private lateinit var doneBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_preferences)

        backBtn = findViewById(R.id.btnAllergyBack)
        doneBtn = findViewById(R.id.btnAllergyDone)

        backBtn.setOnClickListener{
            val intent = Intent(this, userInfo::class.java)
            startActivity(intent)
        }

        doneBtn.setOnClickListener{
            val intent = Intent(this, smartFridgeManager::class.java)
            startActivity(intent)
        }

    }

}