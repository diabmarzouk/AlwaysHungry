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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class addFragment : Fragment(){

    companion object{
         val categorylist =
            arrayListOf("Select from category", "Meat", "Vegetables", "Dairy", "Other")
         lateinit var categorySpinner: Spinner
         lateinit var items :Spinner


    }

    private val calendar = Calendar.getInstance()
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
                if (value == categorylist[0]) {
                    (view as TextView).setTextColor(Color.GRAY)
                } else {
                    //Other selected
                    if (value == categorylist[4]) {
                        //dialog
                        val myDialog = MyDialog()
                        val bundle = Bundle()
                        bundle.putInt(DIALOG_KEY, Category_DIALOG)
                        myDialog.arguments = bundle
                        myDialog.show(getParentFragmentManager(), "tag")
                    }

                    if (firstTimeFlag) {
                        //itemsText
                        itemsText = TextView(context)
                        itemsText.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
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
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        linearLayout?.addView(amountText)

                        //amount Edit Text
                        amountEditText = EditText(context)
                        amountEditText.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        linearLayout?.addView(amountEditText)

                        //unit text
                        unitText = TextView(context)
                        unitText.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        linearLayout?.addView(unitText)

                        //unitSpinner
                        unitSpinner = Spinner(context)
                        unitSpinner.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        linearLayout?.addView(unitSpinner)

                        //textview for Date of Purchase
                        dateText = TextView(context)
                        dateText.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        linearLayout?.addView(dateText)

                        //dialog text
                        dialogText = TextView(context)
                        dialogText.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        linearLayout?.addView(dialogText)

                        //add Item button
                        addItemBtn = Button(context)
                        addItemBtn.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        linearLayout?.addView(addItemBtn)

                        firstTimeFlag = false
                    }
                    //textview for items
                    itemsText.setText("Items")
                    itemsText.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.myBrown
                        )
                    )
                    itemsText.setTextSize(20F)
                    itemsText.typeface = Typeface.defaultFromStyle(Typeface.BOLD)


                    //adding meat items spinner
                    if (value == categorylist[1]) {
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
                    else if (value == categorylist[2]) {
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
                    else if (value == categorylist[3]) {
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
                    amountText.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.myBrown
                        )
                    )
                    amountText.setTextSize(20F)
                    amountText.typeface = Typeface.defaultFromStyle(Typeface.BOLD)

                    //edittext
                    amountEditText.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.white
                        )
                    )
                    amountEditText.setText("1")

                    //textview
                    unitText.setText("Unit")
                    unitText.setTextColor(ContextCompat.getColor(requireContext(), R.color.myBrown))
                    unitText.setTextSize(20F)
                    unitText.typeface = Typeface.defaultFromStyle(Typeface.BOLD)


                    //unit spinner
                    unitSpinner.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.white
                        )
                    )


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
                    dateText.setTextColor(ContextCompat.getColor(requireContext(), R.color.myBrown))
                    dateText.setTextSize(20F)
                    dateText.typeface = Typeface.defaultFromStyle(Typeface.BOLD)

                    //TextView
                    dialogText.text = "Click to choose a date"
                    dialogText.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.white
                        )
                    )
                    dialogText.setTextColor(Color.GRAY)
                    dialogText.setTextSize(17F)
                    dialogText.typeface = Typeface.defaultFromStyle(Typeface.ITALIC)


                    fun updateDateInView() {
                        val myFormat = "MM/dd/yyyy" // mention the format you need
                        val sdf = SimpleDateFormat(myFormat, Locale.US)
                        dialogText!!.text = sdf.format(calendar.getTime())
                    }

                    val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                        override fun onDateSet(
                            view: DatePicker, year: Int, monthOfYear: Int,
                            dayOfMonth: Int
                        ) {
                            calendar.set(Calendar.YEAR, year)
                            calendar.set(Calendar.MONTH, monthOfYear)
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                            updateDateInView()
                        }
                    }
                    dialogText.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(view: View) {
                            DatePickerDialog(
                                requireContext(),
                                dateSetListener,
                                // set DatePickerDialog to point to today's date when it loads up
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                            ).show()
                        }

                    })

                    //add Item button
                    addItemBtn.text = "Add Item"
                    addItemBtn.setTextSize(20F)
                    addItemBtn.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.myBrown
                        )
                    )
                    addItemBtn.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.background
                        )
                    )
                    addItemBtn.setPadding(70, 10, 70, 10)
                    //set margins
                    val param = addItemBtn.layoutParams as ViewGroup.MarginLayoutParams
                    param.setMargins(50, 50, 20, 20)
                    addItemBtn.layoutParams = param
                    addItemBtn.gravity = Gravity.CENTER_HORIZONTAL
                    addItemBtn.gravity = Gravity.CENTER_VERTICAL
                    addItemBtn.setOnClickListener {
                        if (items.selectedItem == "Choose a meat" || items.selectedItem == "Choose a vegetable" || items.selectedItem == "Choose a dairy" || categorySpinner.selectedItem == "Select from Category") {
                            //ask user to choose them before adding the item
                            Toast.makeText(
                                requireContext(), "Specify the product please",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {

                            //data base
                            authObj = Firebase.auth
                            val db = Firebase.firestore
                            val user = authObj.currentUser

                            if (categorySpinner.selectedItem.toString() == "Meat") {
                                if (items.selectedItem.toString() == "Beef") {

                                    var beef = mutableMapOf<String, String>()

                                    beef["Quantity"] = amountEditText.text.toString()
                                    beef["Unit"] = unitSpinner.selectedItem.toString()
                                    beef["Date"] = dialogText.text.toString()

//                        db.collection("users").document(user!!.uid)
//                            .collection("items").document("meat").set(hashMapOf("Lamb" to FieldValue.arrayUnion(lamb)))

                                    db.collection("users").document(user!!.uid)
                                        .collection("items").document("meat")
                                        .update("Beef", FieldValue.arrayUnion(beef))

                                } else if (items.selectedItem.toString() == "Pork") {
                                    var Pork = mutableMapOf<String, String>()

                                    Pork["Quantity"] = amountEditText.text.toString()
                                    Pork["Unit"] = unitSpinner.selectedItem.toString()
                                    Pork["Date"] = dialogText.text.toString()

                                    db.collection("users").document(user!!.uid)
                                        .collection("items").document("meat")
                                        .update("Pork", FieldValue.arrayUnion(Pork))

                                } else if (items.selectedItem.toString() == "Lamb") {
                                    var lamb = mutableMapOf<String, String>()

                                    lamb["Quantity"] = amountEditText.text.toString()
                                    lamb["Unit"] = unitSpinner.selectedItem.toString()
                                    lamb["Date"] = dialogText.text.toString()

                                    db.collection("users").document(user!!.uid)
                                        .collection("items").document("meat")
                                        .update("Lamb", FieldValue.arrayUnion(lamb))

                                } else if (items.selectedItem.toString() == "Chicken") {
                                    var chicken = mutableMapOf<String, String>()

                                    chicken["Quantity"] = amountEditText.text.toString()
                                    chicken["Unit"] = unitSpinner.selectedItem.toString()
                                    chicken["Date"] = dialogText.text.toString()

                                    db.collection("users").document(user!!.uid)
                                        .collection("items").document("meat")
                                        .update("Chicken", FieldValue.arrayUnion(chicken))

                                } else if (items.selectedItem.toString() == "Fish") {
                                    var fish = mutableMapOf<String, String>()

                                    fish["Quantity"] = amountEditText.text.toString()
                                    fish["Unit"] = unitSpinner.selectedItem.toString()
                                    fish["Date"] = dialogText.text.toString()

                                    db.collection("users").document(user!!.uid)
                                        .collection("items").document("meat")
                                        .update("Fish", FieldValue.arrayUnion(fish))

                                } else if (items.selectedItem.toString() == "Shrimp") {
                                    var shrimp = mutableMapOf<String, String>()

                                    shrimp["Quantity"] = amountEditText.text.toString()
                                    shrimp["Unit"] = unitSpinner.selectedItem.toString()
                                    shrimp["Date"] = dialogText.text.toString()

                                    db.collection("users").document(user!!.uid)
                                        .collection("items").document("meat")
                                        .update("Shrimp", FieldValue.arrayUnion(shrimp))

                                } else {
                                    //do other
                                }
                            } else if (categorySpinner.selectedItem.toString() == "Vegetables") {
                                //do vegetables
                            } else if (categorySpinner.selectedItem.toString() == "Dairy") {
                                //do dairy
                                 if (items.selectedItem.toString() == "Milk") {
                                    var milk = mutableMapOf<String, String>()

                                     milk["Quantity"] = amountEditText.text.toString()
                                     milk["Unit"] = unitSpinner.selectedItem.toString()
                                     milk["Date"] = dialogText.text.toString()

                                    db.collection("users").document(user!!.uid)
                                        .collection("items").document("Dairy")
                                        .update("Milk", FieldValue.arrayUnion(milk))

                                }
                                else if (items.selectedItem.toString() == "Butter") {
                                     var butter = mutableMapOf<String, String>()

                                     butter["Quantity"] = amountEditText.text.toString()
                                     butter["Unit"] = unitSpinner.selectedItem.toString()
                                     butter["Date"] = dialogText.text.toString()

                                     db.collection("users").document(user!!.uid)
                                         .collection("items").document("Dairy")
                                         .update("Butter", FieldValue.arrayUnion(butter))

                                 }
                                 else if (items.selectedItem.toString() == "Cheese") {
                                     var cheese = mutableMapOf<String, String>()

                                     cheese["Quantity"] = amountEditText.text.toString()
                                     cheese["Unit"] = unitSpinner.selectedItem.toString()
                                     cheese["Date"] = dialogText.text.toString()

                                     db.collection("users").document(user!!.uid)
                                         .collection("items").document("Dairy")
                                         .update("Cheese", FieldValue.arrayUnion(cheese))

                                 }
                                else  if (items.selectedItem.toString() == "yogurt") {
                                    var yogurt = mutableMapOf<String, String>()

                                     yogurt["Quantity"] = amountEditText.text.toString()
                                     yogurt["Unit"] = unitSpinner.selectedItem.toString()
                                     yogurt["Date"] = dialogText.text.toString()

                                    db.collection("users").document(user!!.uid)
                                        .collection("items").document("Dairy")
                                        .update("yogurt", FieldValue.arrayUnion(yogurt))

                                }
                                 else  if (items.selectedItem.toString() == "Cream") {
                                     var cream = mutableMapOf<String, String>()

                                     cream["Quantity"] = amountEditText.text.toString()
                                     cream["Unit"] = unitSpinner.selectedItem.toString()
                                     cream["Date"] = dialogText.text.toString()

                                     db.collection("users").document(user!!.uid)
                                         .collection("items").document("Dairy")
                                         .update("cream", FieldValue.arrayUnion(cream))

                                 }
                                 else if(items.selectedItem.toString() == "Whey") {
                                     var whey = mutableMapOf<String, String>()

                                     whey["Quantity"] = amountEditText.text.toString()
                                     whey["Unit"] = unitSpinner.selectedItem.toString()
                                     whey["Date"] = dialogText.text.toString()

                                     db.collection("users").document(user!!.uid)
                                         .collection("items").document("Dairy")
                                         .update("whey", FieldValue.arrayUnion(whey))

                                 }
                            } else {
                                //do other
                            }


                            Toast.makeText(
                                requireContext(), "Item is saved",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }

                }
            }
        }
            return root
    }
}