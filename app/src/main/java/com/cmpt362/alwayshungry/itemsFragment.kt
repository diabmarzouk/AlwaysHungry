package com.cmpt362.alwayshungry

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.cmpt362.alwayshungry.recipe.RecipeFinder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth


import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.sql.Types.NULL

class itemsFragment : Fragment() {

    private lateinit var authObj: FirebaseAuth
    private lateinit var meatListView: ListView
    private lateinit var porkString: String
    private lateinit var beefString: String
    private lateinit var lambString: String
    private lateinit var chickenString: String
    private lateinit var fishString: String
    private lateinit var shrimpString: String
    var meatList = arrayListOf<String>()

    private lateinit var vegetablesListView: ListView
    var vegList = arrayListOf<String>()

    private lateinit var dairyListView: ListView
    var dairyList = arrayListOf<String>()





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root= inflater.inflate(R.layout.items_fragment, container, false)


        authObj = Firebase.auth
        val db = Firebase.firestore
        val user = authObj.currentUser

        meatListView = root.findViewById(R.id.meatListView)
        vegetablesListView = root.findViewById(R.id.vegetablesListView)
        dairyListView = root.findViewById(R.id.dairyListView)

        db.collection("users").document(user!!.uid).collection("items").document("meat").get().addOnSuccessListener { document ->
            if(document != null){
                Log.d("Meats", "${document.data}")
                if(document.data?.get("Pork") != null){
                    println("debug: we have pork ${document.data?.get("Pork")}")
                    var data = document.data?.get("Pork").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    porkString = "Pork: $amount $unit purchased at $date"
                    meatList.add(porkString)
                }

                if(document.data?.get("Beef") != null){
                    var data = document.data?.get("Beef").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    beefString = "Beef: $amount $unit purchased at $date"
                    meatList.add(beefString)
                }

                if(document.data?.get("Lamb") != null){
                    var data = document.data?.get("Lamb").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    lambString = "Lamb: $amount $unit purchased at $date"
                    meatList.add(lambString)
                    }

                if(document.data?.get("Chicken") != null){
                    var data = document.data?.get("Lamb").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    chickenString = "Chicken: $amount $unit purchased at $date"
                    meatList.add(chickenString)
                }

                if(document.data?.get("Fish") != null){
                    var data = document.data?.get("Lamb").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    fishString = "Fish: $amount $unit purchased at $date"
                    meatList.add(fishString)
                }

                if(document.data?.get("Shrimp") != null){
                    var data = document.data?.get("Lamb").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    shrimpString = "Shrimp: $amount $unit purchased at $date"
                    meatList.add(shrimpString)
                }

                val arrayAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1, android.R.id.text1,meatList)
                meatListView.adapter = arrayAdapter

            }else{
                Log.d("Error:", "Does not exist")
            }
        }


        //vegetables
        db.collection("users").document(user!!.uid).collection("items").document("vegetables").get().addOnSuccessListener { document ->
            if(document != null){
                if(document.data?.get("Asparagus") != null){
                    var data = document.data?.get("Asparagus").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var aspaString = "Asparagus: $amount $unit purchased at $date"
                    vegList.add(aspaString)
                }

                if(document.data?.get("Beet") != null){
                    var data = document.data?.get("Beet").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var beetString = "Beet: $amount $unit purchased at $date"
                    vegList.add(beetString)
                }

                if(document.data?.get("Broccoli") != null){
                    var data = document.data?.get("Broccoli").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var broccoliString = "Broccoli: $amount $unit purchased at $date"
                    vegList.add(broccoliString)
                }

                if(document.data?.get("Brussels Sprouts") != null){
                    var data = document.data?.get("Brussels Sprouts").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var brusString = "Brussels Sprouts: $amount $unit purchased at $date"
                    vegList.add(brusString)
                }

                if(document.data?.get("Cabbage") != null){
                    var data = document.data?.get("Cabbage").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var cabString = "Cabbage: $amount $unit purchased at $date"
                    vegList.add(cabString)
                }

                if(document.data?.get("Carrot") != null){
                    var data = document.data?.get("Carrot").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var carrotString = "Carrot: $amount $unit purchased at $date"
                    vegList.add(carrotString)
                }

                if(document.data?.get("Cauliflower") != null){
                    var data = document.data?.get("Cauliflower").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var cauliString = "Cauliflower: $amount $unit purchased at $date"
                    vegList.add(cauliString)
                }

                if(document.data?.get("Eggplant") != null){
                    var data = document.data?.get("Eggplant").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var eggString = "Eggplant: $amount $unit purchased at $date"
                    vegList.add(eggString)
                }

                if(document.data?.get("Garlic") != null){
                    var data = document.data?.get("Garlic").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var garlicString = "Garlic: $amount $unit purchased at $date"
                    vegList.add(garlicString)
                }
                if(document.data?.get("Tomatoes") != null){
                    var data = document.data?.get("Tomatoes").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var tomString = "Tomatoes: $amount $unit purchased at $date"
                    vegList.add(tomString)
                }

                if(document.data?.get("Potatoes") != null){
                    var data = document.data?.get("Potatoes").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var potString = "Potatoes: $amount $unit purchased at $date"
                    vegList.add(potString)
                }

                val arrayAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1, android.R.id.text1,vegList)
                vegetablesListView.adapter = arrayAdapter

            }else{
                Log.d("Error:", "Does not exist")
            }
        }

        //dairy
        db.collection("users").document(user!!.uid).collection("items").document("dairy").get().addOnSuccessListener { document ->
            if(document != null){
                if(document.data?.get("Milk") != null){
                    var data = document.data?.get("Milk").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var milkString = "Milk: $amount $unit purchased at $date"
                    dairyList.add(milkString)
                }

                if(document.data?.get("Butter") != null){
                    var data = document.data?.get("Butter").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var butterString = "Butter: $amount $unit purchased at $date"
                    dairyList.add(butterString)
                }

                if(document.data?.get("Cheese") != null){
                    var data = document.data?.get("Cheese").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var cheeseString = "Cheese: $amount $unit purchased at $date"
                    dairyList.add(cheeseString)
                }

                if(document.data?.get("yogurt") != null){
                    var data = document.data?.get("yogurt").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var yogurtString = "yogurt: $amount $unit purchased at $date"
                    dairyList.add(yogurtString)
                }

                if(document.data?.get("Cream") != null){
                    var data = document.data?.get("Cream").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var creamString = "Cream: $amount $unit purchased at $date"
                    dairyList.add(creamString)
                }

                if(document.data?.get("Whey") != null){
                    var data = document.data?.get("Whey").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit = data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var wheyString = "Whey: $amount $unit purchased at $date"
                    dairyList.add(wheyString)
                }

                val arrayAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1, android.R.id.text1,dairyList)
                dairyListView.adapter = arrayAdapter

            }else{
                Log.d("Error:", "Does not exist")
            }
        }






        // Recipe API related code
        val findRecipeBtn = root.findViewById<Button>(R.id.findRecipeBtn)
        findRecipeBtn.setOnClickListener {
            val intent = Intent(context, RecipeFinder::class.java)
            startActivity(intent)
        }





        return root
    }
}