package com.cmpt362.alwayshungry

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import com.cmpt362.alwayshungry.auth.LoginActivity
import com.cmpt362.alwayshungry.auth.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

//Fragment for Setting tab
class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener{
    private lateinit var emailPreference: EditTextPreference
    private lateinit var passwordPreference:EditTextPreference
    private lateinit var logoutButtonPreference: Preference
    private lateinit var webpagePreference: Preference
    private lateinit var unitPreference: ListPreference
//    private lateinit var viewModel: ViewModelBetweenFragments
    private lateinit var sp: SharedPreferences
//    override fun onCreateView(
//        inflater:LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View?{
//        return inflater.inflate(R.layout.fragment_setting,container, false)
//    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        println("onCreatePreferences is just called")
    }


    override fun onPreferenceChange(preference: Preference, value: Any?): Boolean {
        val stringValue = value.toString()

        println("PreferenceChange happened")
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences)
        val currUser = Firebase.auth.currentUser
        var email = currUser?.email
        try{

            println("debug: just updated the userPasword")
        }
        catch(err:Exception){
           println("debug: ${err}")
        }
        println("debug: email of the currUser is like this ${email}")


//        viewModel = ViewModelProvider(requireActivity()).get(ViewModelBetweenFragments::class.java)
//        viewModel.Unit.observe(this){
//            println("debug: from FragmentSetting. I observe that the prefered unit has changed")
//        }



        emailPreference= findPreference("email_pref")!!
        passwordPreference= findPreference("password_pref")!!
        logoutButtonPreference= findPreference("logoutButton")!!
        emailPreference.setOnPreferenceChangeListener { preference, newValue ->
//            currUser?.updateEmail("${newValue}")
            try {
                currUser!!.updateEmail("${newValue}")
                println("debug: after updateEmail")
                Toast.makeText(requireActivity(), "you just update the email  of the currentUser in the DB", Toast.LENGTH_SHORT).show()
            }
            catch(err:Exception){
                println("debug: ${err}")

            }
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                       println("debug: email update successful")
//                    }
//                }


            true

        }
        passwordPreference.setOnPreferenceChangeListener { preference, newValue ->
            try {
                currUser?.updatePassword("${newValue}")
                Toast.makeText(requireActivity(), "you just update the password  of the currentUser in the DB", Toast.LENGTH_SHORT).show()
            }
            catch (err:Exception){
                println("debug: ${err}")
            }
            true

        }
        logoutButtonPreference.setOnPreferenceClickListener {
            println("debug: you log out")
            FirebaseAuth.getInstance().signOut();
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            true
        }
//        unitPreference = findPreference("edit_pref2")!!
//        sp = requireActivity().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE)
//        val editor: SharedPreferences.Editor = sp.edit()
//        var unitValue =0
//        if(unitPreference.value==null){
//            var unitValue =0
//        }
//        else{
//            var unitValue =unitPreference.value.toInt()
//        }
//        editor.putInt("unitType", unitValue)
//        editor.commit()
//        unitPreference.setOnPreferenceChangeListener { preference, newValue ->
//            println("debug: hey this is PreferencechangeListener, from unitPreference")
//            unitValue = newValue.toString().toInt()
//
//            viewModel.Unit.value = unitValue
//            println("debug: (FragmentSetting). viewModel.Unit.value is ${viewModel.Unit.value}")
//            var newValueToReturn = newValue.toString().toInt()
//            println("debug: unitValue is ${newValueToReturn} in setOnPreferenceChange in FragmentSetting.")
//            editor.putInt("unitType", newValueToReturn)
//            editor.commit()
//            true }



//        println("debug: in FragmentSetting, unitValue => ${unitValue}")
//        preference.setOnPreferenceClickListener {
//            println("the profile preference is clicked")
//            println("debug: this click will take you to the profile page.")
////            val intent = Intent(requireActivity(), Profile::class.java)
////            startActivity(intent)
//            true
//        }
//        webpagePreference = findPreference("webpagePref")!!
//        webpagePreference.setOnPreferenceClickListener {
//            println("I will take you to the web link. don't worry")
//            val url ="https://www.sfu.ca/computing.html"
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse(url)
//            startActivity(intent)
//            true
//        }

    }
}
