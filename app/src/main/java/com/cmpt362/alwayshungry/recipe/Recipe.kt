package com.cmpt362.alwayshungry.recipe

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import org.json.JSONObject

data class Recipe(
    val id:String = "",
    val name:String = "",
    val thumbnailEnabled:Boolean = false,
    val thumbnailURL:String = "",
    var saved:Boolean = false,
) {
    fun printRecipe() {
        println("RECIPE DETAILS ----------------------------------------")
        println("id: $id")
        println("name: $name")
        if(thumbnailEnabled) {
            println("thumbnail: $thumbnailURL")
        }else {
            println("thumbnail: N/A")
        }
//        getSavedStatus()
        if(saved) {
            println("saved: YES")
        }else {
            println("saved: NO")
        }
        println("-------------------------------------------------------\n\n\n")
    }

    fun printSavedRecipe() {
        if(saved) {
            println("RECIPE DETAILS ----------------------------------------")
            println("id: $id")
            println("name: $name")
            if(thumbnailEnabled) {
                println("thumbnail: $thumbnailURL")
            }else {
                println("thumbnail: N/A")
            }
            println("saved: YES")
            println("-------------------------------------------------------\n\n\n")
        }
    }

    // gets full recipe information from api using ID
    // *** HAVE NOT TESTED IF THIS FUNCTION WORKS YET
    fun getFullRecipe(): JSONObject {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://tasty.p.rapidapi.com/recipes/get-more-info?id=$id")
            .get()
            .addHeader("X-RapidAPI-Key", "f2a636ebf9msh2acd0f5361689aap107391jsn95efb5c7f5d4")
            .addHeader("X-RapidAPI-Host", "tasty.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()
        val jsonString = response.body()?.string()
        return JSONObject(jsonString)
    }

    fun getSavedStatus() {
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser

        if (user != null) {
            val recipeReference = db.collection("users").document(user.uid).collection("recipes").document(id)
            recipeReference.get().addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    val document = task.result
                    if(document != null) {
                        if(document.exists()) {
                            println("debug: recipe($id) has already been saved to database")
                            saved = true
                        }
                    }
                }
            }
        }
    }

}
