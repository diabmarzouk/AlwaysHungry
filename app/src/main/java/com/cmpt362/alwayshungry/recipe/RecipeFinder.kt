package com.cmpt362.alwayshungry.recipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.children
import com.cmpt362.alwayshungry.R

class RecipeFinder : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_finder)

        val getRecipeBtn = findViewById<Button>(R.id.getRecipeBtn)
        getRecipeBtn.setOnClickListener {
            var tags = getTagStringURL()

            // retrieve the user entered response from search box
            val searchResponse = getEnteredSearchItem()
            val searchEntry = getSearchStringURL(searchResponse)

            //append the two urls
            var appendURL = tags + searchEntry
            println("appendURL_tags is:  $appendURL")// debug

            val recipeRetriever = Intent(this, RecipeRetriever::class.java)
            recipeRetriever.putExtra("urlParameters", appendURL)
            startActivity(recipeRetriever)
        }

        val returnBtn = findViewById<Button>(R.id.returnBtn)
        returnBtn.setOnClickListener {
            finish()
        }

    }

    private fun getTagStringURL(): String {
        val tagCloud = findViewById<LinearLayout>(R.id.tagCloud)
        val tags = tagCloud.children as Sequence<CheckBox>
        var parameterURL = ""
        for (tag in tags) {
            val checked = tag.isChecked
            if (checked) {
                parameterURL = parameterURL.plus("&tags=").plus(tag.tag)
            }
        }
        println("returning: $parameterURL")

        return parameterURL
    }

    private fun getSearchStringURL(enteredSearchBoxString: String): String {
        val listOfParameter_SearchBar = ArrayList<String>()//Creating an empty arraylist.
        listOfParameter_SearchBar.add(enteredSearchBoxString)//Adding object in arraylist.

        var parameterURL_search = ""

        //call the api based on the parameter
        for (search in listOfParameter_SearchBar) {
            parameterURL_search = parameterURL_search.plus("&q=").plus(enteredSearchBoxString)
        }

        return parameterURL_search
    }

    private fun getEnteredSearchItem(): String{ // added
        var response = ""
        val enteredInSearchBar = findViewById<EditText>(R.id.searchItems)
        response = enteredInSearchBar.text.toString()
        return response
    }
}
