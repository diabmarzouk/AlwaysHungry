package com.cmpt362.alwayshungry.recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.sleep
import android.widget.Toast
import androidx.annotation.RestrictTo
import com.cmpt362.alwayshungry.R
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONObject

class RecipeRetriever : AppCompatActivity() {
    val recipes = ArrayList<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_retriever)

        val appendURL = intent.extras!!.getString("urlParameters")
//        println("received appendURL: $appendURL")
        getRecipe(appendURL!!)
    }

    fun getRecipe(appendURL:String) {
        println("debug: getRecipe()")

        val apiURL = "https://tasty.p.rapidapi.com/recipes/list?from=0&size=100$appendURL"

        try {
            GlobalScope.async {
                callRecipeAPI(apiURL)
            }
        }catch (e:Exception) {
            e.printStackTrace()
        }
    }

    private fun callRecipeAPI(apiUrl: String) {
        println("debug: callRecipeAPI()")
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(apiUrl)
            .get()
            .addHeader("X-RapidAPI-Key", "f2a636ebf9msh2acd0f5361689aap107391jsn95efb5c7f5d4")
            .addHeader("X-RapidAPI-Host", "tasty.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()
        val jsonString = response.body()?.string()
        println("jsonString: $jsonString")


        // parse json data
        val jsonObject = JSONObject(jsonString)
        val dataArray = jsonObject.getJSONArray("results")
        println("*** Number of recipes retrieved: ${dataArray.length()}")
        println("Recipes ------------------------------------------------------")
        for(i in 0 until dataArray.length()) {
            val detail = dataArray.getJSONObject(i)
            val id = detail.get("id").toString()
            val name = detail.get("name").toString()
            val description = detail.get("description").toString()

            println("  ${i+1}. $name [$id]")
            if(!isEmpty_Blank_Null(description)) {
                println("      >> $description")
            }

            // if recipe id not a collection of recipes add to list of results
            if(!detail.has("recipes")) {
                // check thumbnail
                var thumbnailEnabled = false
                var thumbnailURL = ""
                if(detail.has("thumbnail_url")) {
                    val thumbnail = detail.get("thumbnail_url").toString()
                    if(!isEmpty_Blank_Null(thumbnail)) {
                        // set Recipe object's "thumbnailEnabled" to true and the thumbnail url
                        thumbnailEnabled = true
                        thumbnailURL = thumbnail
                    }
                }

                // check if already saved in database as favorite recipe
//                TODO("check if already saved in database as favorite recipe")
                var saved = false


                // create new Recipe instance
                val recipe = Recipe(id, name, thumbnailEnabled, thumbnailURL, saved)
                recipe.printRecipe() // debug

                // append to ListArray<Recipe> for the ListView and pass to list adapter
                recipes.add(recipe)
            }else {
                println("COLLECTION OF RECIPES ** not to be added to list\n\n\n")// debug
                // do not include in list A.K.A do nothing here, just ignore this recipe
            }

        }
        println("--------------------------------------------------------------")

        printRecipeList()
    }

    private fun isEmpty_Blank_Null(data:String):Boolean {
        if(data.isBlank() || data.isEmpty()) return true
        if(data == "null" || data == "Null" || data == "NULL") return true
        if(data == null || data == JSONObject.NULL) return true
        return false
    }

    private fun printRecipeList() {// debug
        println("${recipes.size} recipes in the list")
        for(item in recipes) {
            item.printRecipe()
        }
    }
}