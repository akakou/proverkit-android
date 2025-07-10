package com.akakou.proverkit.example


import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import com.akakou.proverkit.AbstractProver

class Passing {}

class SampleProver(): AbstractProver<Passing>() {
    override suspend fun register(context: Context) {
        val configEditor = context.getSharedPreferences("default",  Context.MODE_PRIVATE).edit()
        configEditor.putString("credential", "this is credential").apply()
    }
}