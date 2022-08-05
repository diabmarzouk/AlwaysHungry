package com.cmpt362.alwayshungry

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cmpt362.alwayshungry.auth.MyDialog
import com.cmpt362.alwayshungry.auth.MyDialog.Companion.Category_DIALOG
import com.cmpt362.alwayshungry.auth.MyDialog.Companion.DIALOG_KEY
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList


class addFragment : Fragment(),DatePickerDialog.OnDateSetListener {

    companion object{
         val categorylist =
            arrayListOf("Select from category", "Meat", "Vegetables", "Dairy", "Other")
         lateinit var categorySpinner: Spinner
         lateinit var items :Spinner


    }

    private val calendar = Calendar.getInstance()
    private lateinit var itemSpinner: Spinner
    private lateinit var quantitySpinner: Spinner
    private lateinit var dateET: EditText
    private lateinit var dialogText :TextView
    private var firstTimeFlag: Boolean = true
    private lateinit var itemsText :TextView
    private lateinit var amountText :TextView
    private lateinit var amountEditText :EditText
    private lateinit var unitText :TextView
    private lateinit var unitSpinner :Spinner
    private lateinit var dateText :TextView
    private lateinit var addItemBtn :Button


    private lateinit var authObj: FirebaseAuth





    private val unitList = arrayListOf("L","g","kg","mL","units")
    private val meatlist = arrayListOf("Choose a meat","Beef", "Pork", "Lamb", "Chicken", "Fish", "Shrimp","other")
    private val vegtablelist = arrayListOf(
        "Choose a vegetable",
        "Asparagus",
        "Avocado",
        "Beet",
        "Broccoli",
        "Brussels Sprouts",
        "Cabbage",
        "Carrot",
        "Cauliflower",
        "Celery",
        "Corn",
        "Eggplant",
        "Garlic",
        "Tomatoes",
        "Potatoes"
    )
    private val dairyList = arrayListOf("Choose a dairy","Milk", "Butter", "Cheese", "yogurt", "Cream", "Whey")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.add_fragment, container, false)

        //category spinner
        categorySpinner = root.findViewById(R.id.categorySpinner)
        val linearLayout = root.findViewById<LinearLayout>(R.id.constraint)


        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categorylist)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = spinnerAdapter

        categorySpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val value = parent!!.getItemAtPosition(position).toString()
                if(value == categorylist[0]) {
                    (view as TextView).setTextColor(Color.GRAY)
                }
                else {
                    //Other selected
                    if (value == categorylist[4]) {
                        //dialog
                        val myDialog = MyDialog()
                        val bundle = Bundle()
                        bundle.putInt(DIALOG_KEY,Category_DIALOG)
                        myDialog.arguments = bundle
                        myDialog.show(getParentFragmentManager(), "tag")
                    }

                    if(firstTimeFlag){
                        //itemsText
                        itemsText = TextView(context)
                        itemsText.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                        linearLayout?.addView(itemsText)
                        //itemsSpinner
                        items = Spinner(context)
                        items.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        linearLayout?.addView(items)

                        //amount Text
                        amountText = TextView(context)
                        amountText.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                        linearLayout?.addView(amountText)

                        //amount Edit Text
                        amountEditText = EditText(context)
                        amountEditText.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                        linearLayout?.addView(amountEditText)

                        //unit text
                        unitText = TextView(context)
                        unitText.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                        linearLayout?.addView(unitText)

                        //unitSpinner
                        unitSpinner = Spinner(context)
                        unitSpinner.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                        linearLayout?.addView(unitSpinner)

                        //textview for Date of Purchase
                        dateText = TextView(context)
                        dateText.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                        linearLayout?.addView(dateText)

                        //dialog text
                        dialogText = TextView(context)
                        dialogText.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                        linearLayout?.addView(dialogText)

                        //add Item button
                        addItemBtn = Button(context)
                        addItemBtn.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                        linearLayout?.addView(addItemBtn)

                        firstTimeFlag = false
                    }
                    //textview for items
                    itemsText.setText("Items")
                    itemsText.setTextColor(ContextCompat.getColor(requireContext(),R.color.myBrown))
                    itemsText.setTextSize(20F)
                    itemsText.typeface = Typeface.defaultFromStyle(Typeface.BOLD)


                    //adding meat items spinner
                    if(value == categorylist[1]) {
                        items.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.white
                            )
                        )

                        if (items != null) {
                            val spinnerAdapter2 = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_spinner_item,
                                meatlist
                            )
                            items.adapter = spinnerAdapter2
                        }

                        items?.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {}
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    val value = parent!!.getItemAtPosition(position).toString()
                                    if (value == meatlist[0]) {
                                        (view as TextView).setTextColor(Color.GRAY)
                                    }
                                }
                            }
                    }
                    //adding vegetable items spinner
                    else if(value == categorylist[2]) {
                            items.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.white
                                )
                            )

                            if (items != null) {
                                val spinnerAdapter2 = ArrayAdapter(
                                    requireContext(),
                                    android.R.layout.simple_spinner_item,
                                    vegtablelist
                                )
                                items.adapter = spinnerAdapter2
                            }

                            items?.onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {
                                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                                    override fun onItemSelected(
                                        parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long
                                    ) {
                                        val value = parent!!.getItemAtPosition(position).toString()
                                        if (value == vegtablelist[0]) {
                                            (view as TextView).setTextColor(Color.GRAY)
                                        }
                                    }
                                }
                        }
                    //adding dairy items spinner
                    else if(value == categorylist[3]) {
                        items.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.white
                            )
                        )

                        if (items != null) {
                            val spinnerAdapter2 = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_spinner_item,
                                dairyList
                            )
                            items.adapter = spinnerAdapter2
                        }

                        items?.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {}
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    val value = parent!!.getItemAtPosition(position).toString()
                                    if (value == dairyList[0]) {
                                        (view as TextView).setTextColor(Color.GRAY)
                                    }
                                }
                            }
                    }

                        //textview
                        amountText.setText("Amount")
                        amountText.setTextColor(ContextCompat.getColor(requireContext(),R.color.myBrown))
                        amountText.setTextSize(20F)
                        amountText.typeface = Typeface.defaultFromStyle(Typeface.BOLD)

                        //edittext
                        amountEditText.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.white))
                        amountEditText.setText("   1   ")



                        //textview
                        unitText.setText("Unit")
                        unitText.setTextColor(ContextCompat.getColor(requireContext(),R.color.myBrown))
                        unitText.setTextSize(20F)
                        unitText.typeface = Typeface.defaultFromStyle(Typeface.BOLD)


                        //unit spinner
                        unitSpinner.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.white))


                    if (unitSpinner != null) {
                            val spinnerAdapter2 = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_spinner_item,
                                unitList
                            )
                            unitSpinner.adapter = spinnerAdapter2
                        }
                    
                    //textView
                    dateText.setText("Date of Purchase")
                    dateText.setTextColor(ContextCompat.getColor(requireContext(),R.color.myBrown))
                    dateText.setTextSize(20F)
                    dateText.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    
                    //TextView
                    dialogText.text = "Click to choose a date"
                    dialogText.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.white))
                    dialogText.setTextColor(Color.GRAY)
                    dialogText.setTextSize(17F)
                    dialogText.typeface = Typeface.defaultFromStyle(Typeface.ITALIC)
                    dialogText.setOnClickListener {
                        val datePickerDialog = DatePickerDialog(
                            requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->  },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        )
                        datePickerDialog.show()
                    }


                    //add Item button
                    addItemBtn.text="Add Item"
                    addItemBtn.setTextSize(20F)
                    addItemBtn.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.myBrown))
                    addItemBtn.setTextColor(ContextCompat.getColor(requireContext(),R.color.background))
                    addItemBtn.setPadding(70,10,70,10)
                    //set margins
                    val param = addItemBtn.layoutParams as ViewGroup.MarginLayoutParams
                    param.setMargins(50,50,20,20)
                    addItemBtn.layoutParams = param
                    addItemBtn.gravity = Gravity.CENTER_HORIZONTAL
                    addItemBtn.gravity = Gravity.CENTER_VERTICAL
                    addItemBtn.setOnClickListener {
                        if(items.selectedItem == "Choose a meat" || items.selectedItem == "Choose a vegetable" || items.selectedItem == "Choose a dairy" ){
                            //ask user to choose them before adding the item
                        }

                        //data base
                        authObj = Firebase.auth
                        val db = Firebase.firestore
                        val user = authObj.currentUser
                        //my thoughts:
                        //have an array for each item
                        // if it's chicken go to chicken array and add an entry of [amount#unit#date]
                        //for each array set 1.how many days there are good for 2.how many calories pe gram this array has

                        var lamb = mutableMapOf<String, String>()

                        lamb["Quantity"] = "5"
                        lamb["Unit"] = "Grams"
                        lamb["Date"] = "May 12, 2022"

//                        db.collection("users").document(user!!.uid)
//                            .collection("items").document("meat").set(hashMapOf("Lamb" to FieldValue.arrayUnion(lamb)))

                        db.collection("users").document(user!!.uid)
                            .collection("items").document("meat")
                            .update("Lamb", FieldValue.arrayUnion(lamb))

                    }





                }
            }

        }
        return root
    }


    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        //for some reason onDateSet is not being called, Idk why
         var date = monthOfYear.toString().plus(".".plus(dayOfMonth.toString().plus(".".plus(year.toString().plus(",  ")))))
        dialogText.setText(date)
        println("debug: $date")
    }
}