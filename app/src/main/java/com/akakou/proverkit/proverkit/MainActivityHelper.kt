package com.akakou.proverkit.proverkit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivityHelper(val manager: AbstractProverManager)
{
    lateinit var config : SharedPreferences
    var message = "Before using this app, you need to prove your identity first."

    fun proveIdentityIfNeeded(activity: ComponentActivity,  identificationIntent: Intent) {
        config = activity.getSharedPreferences("default", Context.MODE_PRIVATE)

        val idToken = config.getString("idToken", "")!!
        if (idToken == "") {
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
            proveIdentity(activity, identificationIntent)
        }
    }

    fun proveIdentity(activity: ComponentActivity, identificationIntent: Intent) {
        activity.startActivity(identificationIntent)
    }
}