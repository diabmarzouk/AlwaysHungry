package com.cmpt362.alwayshungry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cmpt362.alwayshungry.recipe.Recipe
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class recipesFragment : Fragment() {
    val savedRecipes = ArrayList<Recipe>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recipes_fragment, container, false)


        // get all saved recipes from database
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser

        db.collection("users").document(user!!.uid)
            .collection("recipes").get().addOnSuccessListener { documents ->
                for(document in documents) {
                    val id = document.get("recipe_id").toString()
                    val name = document.get("recipe_name").toString()
                    val thumbnailStatus = document.get("recipe_thumbnail_enabled").toString()
                    val thumbnail = document.get("recipe_thumbnail_url").toString()
                    if(thumbnailStatus == "true") {
                        val recipe = Recipe(id, name, true, thumbnail, true)
                        savedRecipes.add(recipe)
                        recipe.printRecipe()
                    }else {
                        val recipe = Recipe(id, name, false, thumbnail, true)
                        savedRecipes.add(recipe)
                        recipe.printRecipe()
                    }

                }
            }

        return view
    }
}