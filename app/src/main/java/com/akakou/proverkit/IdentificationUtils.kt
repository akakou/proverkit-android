package com.akakou.proverkit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

class IdentificationUtils(val activity: Activity)
{
    var message = "Before using this app, you need to prove your identity first."

    fun hasIdentified() : Boolean{
        val config = activity.getSharedPreferences("default", Context.MODE_PRIVATE)
        val idToken = config.getString("idToken", "")!!

        return idToken != ""
    }

    fun  <T: Activity> proveIdentity(destination: Class<T>) {
        val intent = Intent(activity, destination)

        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        activity.startActivity(intent)
    }
}
