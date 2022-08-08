package com.cmpt362.alwayshungry.recipe

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

data class Recipe(
    val id:String = "",
    val name:String = "",
    val thumbnailEnabled:Boolean = false,
    val thumbnailURL:String = "",
    var saved:Boolean = false
) {
    fun printRecipe() {
        println("RECIPE DETAILS ----------------------------------------")
        println("id: $id")
        println("name: $name")
        if(thumbnailEnabled) {
            println("thumbnail url: $thumbnailURL")
        }else {
            println("NO THUMBNAIL")
        }
        if(saved) {
            println("SAVED")
        }else {
            println("NOT SAVED")
        }
        println("-------------------------------------------------------\n\n\n")
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
        val jsonString = response.body?.string()
        return JSONObject(jsonString)
    }
}