package com.akakou.proverkit.utils

import android.app.Activity
import android.content.Context


fun hasIdentified(activity: Activity) : Boolean{
    val config = activity.getSharedPreferences("default", Context.MODE_PRIVATE)
    val idToken = config.getString("idToken", "")!!
    return idToken != ""
}

fun hasRegistered(activity: Activity) : Boolean{
    val config = activity.getSharedPreferences("default", Context.MODE_PRIVATE)
    val credential = config.getString("credential", "")!!
    return credential != ""
}

