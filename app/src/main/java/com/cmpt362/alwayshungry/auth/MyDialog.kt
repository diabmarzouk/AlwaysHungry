package com.cmpt362.alwayshungry.auth

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.method.DigitsKeyListener
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.cmpt362.alwayshungry.R
import com.cmpt362.alwayshungry.addFragment
import com.cmpt362.alwayshungry.addFragment.Companion.categorylist

class MyDialog: DialogFragment(), DialogInterface.OnClickListener {
    private lateinit var myEditbox: EditText
    private var myDialogId: Int? = null
    companion object {
        const val DIALOG_KEY = "dialog"
        const val Category_DIALOG = 1
        const val Items_DIALOG = 2
        var otherCategory =""
        var otherItem =""

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        lateinit var dialog: Dialog
        val bundle = arguments
        myDialogId = bundle?.getInt(DIALOG_KEY)
        if( myDialogId == Category_DIALOG) {
            val view = requireActivity().layoutInflater.inflate(R.layout.my_dialog, null)
            //update category spinner
            myEditbox = view.findViewById<EditText>(R.id.myEdittext)
            val builder = AlertDialog.Builder(requireActivity())
            builder.setView(view)
            builder.setTitle("Select a Category")
            builder.setPositiveButton("Save", this)
            builder.setNegativeButton("Cancel", this)
            dialog = builder.create()
        }
        else if( myDialogId == Category_DIALOG) {
            val view = requireActivity().layoutInflater.inflate(R.layout.my_dialog, null)
            myEditbox = view.findViewById<EditText>(R.id.myEdittext)
            val builder = AlertDialog.Builder(requireActivity())
            builder.setView(view)
            builder.setTitle("Select an Item")
            builder.setPositiveButton("Save", this)
            builder.setNegativeButton("Cancel", this)
            dialog = builder.create()
        }
        return dialog
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onClick(dialog: DialogInterface?, item: Int) {
        if(item == DialogInterface.BUTTON_POSITIVE) {
            if( myDialogId == Category_DIALOG) {
                Toast.makeText(requireActivity(), "New Category Saved", Toast.LENGTH_SHORT).show()
                otherCategory = myEditbox.text.toString()
                categorylist.add(0, otherCategory)
                val spinnerAdapter =
                    ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categorylist)
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                addFragment.categorySpinner.adapter = spinnerAdapter

                //update items spinner
                val myItem = arrayListOf("Choose an item for this Category","Others")
                val spinnerAdapter2 = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    myItem
                )
                addFragment.items.adapter = spinnerAdapter2
                addFragment.items.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                addFragment.items?.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            val value = parent!!.getItemAtPosition(position).toString()
                            if (value ==myItem[0]) {
                                (view as TextView).setTextColor(Color.GRAY)
                            }
                        }
                    }

            }
            else if( myDialogId == Items_DIALOG) {
                Toast.makeText(requireActivity(), "New Item Saved", Toast.LENGTH_SHORT).show()
                otherItem = myEditbox.text.toString()

            }
        }
        else if (item == DialogInterface.BUTTON_NEGATIVE)
            Toast.makeText(requireActivity(), "Canceled", Toast.LENGTH_SHORT).show()
    }
}