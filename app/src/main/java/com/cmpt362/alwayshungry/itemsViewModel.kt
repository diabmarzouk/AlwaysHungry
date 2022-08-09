package com.cmpt362.alwayshungry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class itemsViewModel : ViewModel() {

    private lateinit var porkString: String
    private lateinit var beefString: String
    private lateinit var lambString: String
    private lateinit var chickenString: String
    private lateinit var fishString: String
    private lateinit var shrimpString: String

    private val meatList: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>().also{
            loadMeatList()
        }
    }

    private val vegList: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>().also{
            loadVegList()
        }
    }

    private val dairyList: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>().also{
            loadDairyList()
        }
    }

    fun getMeatList() : LiveData<ArrayList<String>>{
        return meatList
    }

    fun getVegList() : LiveData<ArrayList<String>>{
        return vegList
    }

    fun getDairyList() : LiveData<ArrayList<String>>{
        return dairyList
    }

    private fun loadMeatList() {
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser

        db.collection("users").document(user!!.uid).collection("items").document("meat").get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("Meats", "${document.data}")
                    if (document.data?.get("Pork") != null) {
                        println("debug: we have pork ${document.data?.get("Pork")}")
                        var data = document.data?.get("Pork").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        porkString = "Pork: $amount $unit purchased at $date"
                        meatList.value?.add(porkString)
                    }

                    if (document.data?.get("Beef") != null) {
                        var data = document.data?.get("Beef").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        beefString = "Beef: $amount $unit purchased at $date"
                        meatList.value?.add(beefString)
                    }

                    if (document.data?.get("Lamb") != null) {
                        var data = document.data?.get("Lamb").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        lambString = "Lamb: $amount $unit purchased at $date"
                        meatList.value?.add(lambString)
                    }

                    if (document.data?.get("Chicken") != null) {
                        var data = document.data?.get("Lamb").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        chickenString = "Chicken: $amount $unit purchased at $date"
                        meatList.value?.add(chickenString)
                    }

                    if (document.data?.get("Fish") != null) {
                        var data = document.data?.get("Lamb").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        fishString = "Fish: $amount $unit purchased at $date"
                        meatList.value?.add(fishString)
                    }

                    if (document.data?.get("Shrimp") != null) {
                        var data = document.data?.get("Lamb").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        shrimpString = "Shrimp: $amount $unit purchased at $date"
                        meatList.value?.add(shrimpString)
                    }
                }

            }
    }


    private fun loadVegList() {
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser

        db.collection("users").document(user!!.uid).collection("items").document("vegetables").get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    if (document.data?.get("Asparagus") != null) {
                        var data = document.data?.get("Asparagus").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        var aspaString = "Asparagus: $amount $unit purchased at $date"
                        vegList.value?.add(aspaString)
                    }

                    if (document.data?.get("Beet") != null) {
                        var data = document.data?.get("Beet").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        var beetString = "Beet: $amount $unit purchased at $date"
                        vegList.value?.add(beetString)
                    }

                    if (document.data?.get("Broccoli") != null) {
                        var data = document.data?.get("Broccoli").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        var broccoliString = "Broccoli: $amount $unit purchased at $date"
                        vegList.value?.add(broccoliString)
                    }

                    if (document.data?.get("Brussels Sprouts") != null) {
                        var data = document.data?.get("Brussels Sprouts").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        var brusString = "Brussels Sprouts: $amount $unit purchased at $date"
                        vegList.value?.add(brusString)
                    }

                    if (document.data?.get("Cabbage") != null) {
                        var data = document.data?.get("Cabbage").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        var cabString = "Cabbage: $amount $unit purchased at $date"
                        vegList.value?.add(cabString)
                    }

                    if (document.data?.get("Carrot") != null) {
                        var data = document.data?.get("Carrot").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        var carrotString = "Carrot: $amount $unit purchased at $date"
                        vegList.value?.add(carrotString)
                    }

                    if (document.data?.get("Cauliflower") != null) {
                        var data = document.data?.get("Cauliflower").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        var cauliString = "Cauliflower: $amount $unit purchased at $date"
                        vegList.value?.add(cauliString)
                    }

                    if (document.data?.get("Eggplant") != null) {
                        var data = document.data?.get("Eggplant").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        var eggString = "Eggplant: $amount $unit purchased at $date"
                        vegList.value?.add(eggString)
                    }

                    if (document.data?.get("Garlic") != null) {
                        var data = document.data?.get("Garlic").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        var garlicString = "Garlic: $amount $unit purchased at $date"
                        vegList.value?.add(garlicString)
                    }
                    if (document.data?.get("Tomatoes") != null) {
                        var data = document.data?.get("Tomatoes").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        var tomString = "Tomatoes: $amount $unit purchased at $date"
                        vegList.value?.add(tomString)
                    }

                    if (document.data?.get("Potatoes") != null) {
                        var data = document.data?.get("Potatoes").toString()
                        var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                        var unit =
                            data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                        var date = data.substringAfter("Date=").substringBefore("}]")
                        var potString = "Potatoes: $amount $unit purchased at $date"
                        vegList.value?.add(potString)
                    }

                }
            }
    }

    private fun loadDairyList(){
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser

        db.collection("users").document(user!!.uid).collection("items").document("dairy").get().addOnSuccessListener { document ->
            if (document != null) {
                if (document.data?.get("Milk") != null) {
                    var data = document.data?.get("Milk").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit =
                        data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var milkString = "Milk: $amount $unit purchased at $date"
                    dairyList.value?.add(milkString)
                }

                if (document.data?.get("Butter") != null) {
                    var data = document.data?.get("Butter").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit =
                        data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var butterString = "Butter: $amount $unit purchased at $date"
                    dairyList.value?.add(butterString)
                }

                if (document.data?.get("Cheese") != null) {
                    var data = document.data?.get("Cheese").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit =
                        data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var cheeseString = "Cheese: $amount $unit purchased at $date"
                    dairyList.value?.add(cheeseString)
                }

                if (document.data?.get("yogurt") != null) {
                    var data = document.data?.get("yogurt").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit =
                        data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var yogurtString = "yogurt: $amount $unit purchased at $date"
                    dairyList.value?.add(yogurtString)
                }

                if (document.data?.get("Cream") != null) {
                    var data = document.data?.get("Cream").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit =
                        data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var creamString = "Cream: $amount $unit purchased at $date"
                    dairyList.value?.add(creamString)
                }

                if (document.data?.get("Whey") != null) {
                    var data = document.data?.get("Whey").toString()
                    var amount = data.substringAfter("Quantity=").substringBefore(", Unit")
                    var unit =
                        data.substringAfter("Quantity=$amount, Unit=").substringBefore(", Date")
                    var date = data.substringAfter("Date=").substringBefore("}]")
                    var wheyString = "Whey: $amount $unit purchased at $date"
                    dairyList.value?.add(wheyString)
                }
            }
        }

    }

}