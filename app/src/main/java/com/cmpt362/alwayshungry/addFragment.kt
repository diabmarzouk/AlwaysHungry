package com.cmpt362.alwayshungry

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import java.util.*

class addFragment : Fragment() {

    private val calendar = Calendar.getInstance()
    private lateinit var categorySpinner: Spinner
    private lateinit var itemSpinner: Spinner
    private lateinit var quantitySpinner: Spinner
    private lateinit var dateET: EditText


    private val categorylist =
        arrayListOf("Select from category", "Meat", "Vegetables", "Dairy", "Other")
    private val quantitylist = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    private val unitList = arrayListOf("L","g","kg","mL","units")
    private val meatlist = arrayListOf("Select a meat","Beef", "Pork", "Lamb", "Chicken", "Fish", "Shrimp")
    private val vegtablelist = arrayListOf(
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
    private val dairyList = arrayListOf("Milk", "Butter", "Cheese", "yogurt", "Cream", "Whey")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.add_fragment, container, false)

        //category spinner
        categorySpinner = root.findViewById(R.id.categorySpinner)
        val linearLayout = root.findViewById<LinearLayout>(R.id.constraint)
        //val linearLayout2 = root.findViewById<LinearLayout>(R.id.linear)


//        itemSpinner = root.findViewById(R.id.itemSpinner)
//        quantitySpinner = root.findViewById(R.id.quantitySpinner)
//        dateET = root.findViewById(R.id.dateET)

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
                }
                //meat selected
                else if (value == categorylist[1]) {
                    //textview for items
                    val itemsText = TextView(context)
                    itemsText.layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
                    linearLayout?.addView(itemsText)
                    itemsText.setText("Items")
                    itemsText.setTextColor(Color.parseColor("#FF000000"))


                    //adding meat items spinner
                    val items = Spinner(context)
                    items.layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
                    linearLayout?.addView(items)

                    if (items != null) {
                        val spinnerAdapter2 = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            meatlist
                        )
                        items.adapter = spinnerAdapter2
                    }

                    items?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }

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

                            //textview
                            val amountText = TextView(context)
                            amountText.layoutParams = LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT)
                            linearLayout?.addView(amountText)
                            amountText.setText("Amount")
                            amountText.setTextColor(Color.parseColor("#FF000000"))

                            //edittext
                            val amountEditText = EditText(context)
                            amountEditText.layoutParams = LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT)
                            linearLayout?.addView(amountEditText)
                            amountEditText.setText("1")

                            //textview
                            val unitText = TextView(context)
                            unitText.layoutParams = LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT)
                            linearLayout?.addView(unitText)
                            unitText.setText("Unit")
                            unitText.setTextColor(Color.parseColor("#FF000000"))


                            //unit spinner
                            val unitSpinner = Spinner(context)
                            unitSpinner.layoutParams = LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT)
                            linearLayout?.addView(unitSpinner)

                            if (unitSpinner != null) {
                                val spinnerAdapter2 = ArrayAdapter(
                                    requireContext(),
                                    android.R.layout.simple_spinner_item,
                                    unitList
                                )
                                unitSpinner.adapter = spinnerAdapter2
                            }



                        }


                        }









                }
                //vegetable selected
                else if (value == categorylist[2]) {
                    val spinnerAdapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        vegtablelist
                    )
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    itemSpinner.adapter = spinnerAdapter
                    val spinnerAdapter2 = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        quantitylist
                    )
                    spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    quantitySpinner.adapter = spinnerAdapter2
                } else if (value == categorylist[3]) {
                    val spinnerAdapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        dairyList
                    )
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    itemSpinner.adapter = spinnerAdapter
                }




            }

        }
        return root
    }


}