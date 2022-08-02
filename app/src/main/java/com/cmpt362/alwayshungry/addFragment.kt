package com.cmpt362.alwayshungry

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.Fragment

class addFragment : Fragment() {
    private lateinit var categorySpinner: Spinner
    private lateinit var itemSpinner: Spinner
    private lateinit var quantitySpinner: Spinner

    private val categorylist = arrayListOf("Select the category","Meat", "Vegetables", "Dairy","Other")
    private val quantitylist = arrayListOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)
    private val meatportionList = arrayListOf("100g","200g","300g","400g","500g","600g","700g","800g","900g","1kg","2kg","3kg")
    private val meatlist = arrayListOf("Beef","Pork","Lamb", "Chicken", "Fish", "Shrimp")
    private val vegtablelist = arrayListOf("Asparagus","Avocado","Beet","Broccoli","Brussels Sprouts","Cabbage","Carrot","Cauliflower","Celery","Corn","Eggplant","Garlic","Tomatoes","Potatoes")
    private val dairyList = arrayListOf("Milk","Butter","Cheese","yogurt","Cream","Whey")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.add_fragment, container, false)

        //category spinner
        categorySpinner = root.findViewById(R.id.categorySpinner)
        val spinnerAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,categorylist)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = spinnerAdapter

        itemSpinner = root.findViewById(R.id.itemSpinner)
        quantitySpinner = root.findViewById(R.id.quantitySpinner)


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
                if(value == categorylist[0]){
                    (view as TextView).setTextColor(Color.GRAY)
                }
                else if(value == categorylist[1]){
                    val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, meatlist)
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    itemSpinner.adapter = spinnerAdapter
                    val spinnerAdapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, meatportionList)
                    spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    quantitySpinner.adapter = spinnerAdapter2
                }
                else if(value == categorylist[2]){
                    val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, vegtablelist)
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    itemSpinner.adapter = spinnerAdapter
                    val spinnerAdapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, quantitylist)
                    spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    quantitySpinner.adapter = spinnerAdapter2
                }
                else if(value == categorylist[3]){
                    val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dairyList)
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    itemSpinner.adapter = spinnerAdapter
                }

            }
        }

//            if(categorySpinner.selectedItem == "Meat")
//            {
//
//            } else if (categorySpinner.selectedItem == "Vegetable")
//            {
//                val spinnerAdapter = ArrayAdapter(
//                    requireContext(),
//                    android.R.layout.simple_spinner_item,
//                    vegtablelist
//                )
//                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                itemSpinner.adapter = spinnerAdapter
//            } else if (categorySpinner.selectedItem == "Dairy")
//            {
//                val spinnerAdapter =
//                    ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dairyList)
//                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                itemSpinner.adapter = spinnerAdapter
//            }





        return root
    }

}