package com.cmpt362.alwayshungry.recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.cmpt362.alwayshungry.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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

    private suspend fun callRecipeAPI(apiUrl: String) {
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

            val db = Firebase.firestore
            val user = Firebase.auth.currentUser

            // if recipe id not a collection of recipes add to list of results
            if(!detail.has("recipes")) {
                val id = detail.get("id").toString()
                val name = detail.get("name").toString()
//                println("  ${i+1}. $name [$id]")


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
                var saved = false
                val coroutine = GlobalScope.launch {
                    if (user != null) {
                        val recipeReference = db.collection("users").document(user.uid).collection("recipes").document(id)
                        recipeReference.get().addOnCompleteListener { task ->
                            if(task.isSuccessful) {
                                val document = task.result
                                if(document != null) {
                                    if(document.exists()) {
                                        println("debug: recipe($id) has already been saved to database")
                                        saved = true
                                        val recipe = Recipe(id, name, thumbnailEnabled, thumbnailURL, true)
                                        recipes.add(recipe)
                                        recipe.printRecipe()

                                    }else {
                                        val recipe = Recipe(id, name, thumbnailEnabled, thumbnailURL, false)
                                        recipes.add(recipe)
                                        recipe.printRecipe()
                                    }
                                }
                            }
                        }
                    }
                }

                coroutine.join()

                println("GETTING SAVED STATUS FINISHED: $saved")


                // create new Recipe instance
//                val recipe = Recipe(id, name, thumbnailEnabled, thumbnailURL, saved)
//                recipe.printRecipe() // debug
//                    recipe.printSavedRecipe()

                // append to ListArray<Recipe> for the ListView and pass to list adapter
//                recipes.add(recipe)

            }

        }
        println("--------------------------------------------------------------")

        printRecipeList()
//        TODO("Load ListView here")

        // after UI updated with recipes
        val saveRecipeBtn = findViewById<Button>(R.id.saveRecipeBtn)
        saveRecipeBtn.setOnClickListener {
            for(i in 0 until recipes.size) {
                if(i%10 == 0) {
                    saveRecipe(recipes[i])
                }
            }
        }
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

    // save recipe to the firebase
    private fun saveRecipe(recipe:Recipe) {
        if(!recipe.saved) {
            val db = Firebase.firestore
            val user = Firebase.auth.currentUser

            // create map object
            val recipeMapObj = mutableMapOf<String, String>()
            recipeMapObj["recipe_id"] = recipe.id
            recipeMapObj["recipe_name"] = recipe.name
            recipeMapObj["recipe_thumbnail_enabled"] = if(recipe.thumbnailEnabled) {"true"} else {"false"}
            recipeMapObj["recipe_thumbnail_url"] = recipe.thumbnailURL
            recipeMapObj["recipe_saved"] = "true"


            // save new recipe to recipes collection in firebase
            if (user != null) {
                db.collection("users").document(user.uid)
                    .collection("recipes").document(recipe.id)
                    .set(recipeMapObj)
            }
        }
    }

    // remove saved recipe from firebase
    private fun removeRecipe(recipe: Recipe) {
        if(recipe.saved) {
            val db = Firebase.firestore
            val user = Firebase.auth.currentUser
            val recipeID = recipe.id

            // remove saved recipe from recipes collection in firebase
            val recipeReference = db.collection("users").document(user!!.uid).collection("recipes").document(recipeID)
            recipeReference.delete().addOnSuccessListener {
                Toast.makeText(this, "Recipe deleted.", Toast.LENGTH_SHORT).show()
                // update UI
            }
        }
    }
}