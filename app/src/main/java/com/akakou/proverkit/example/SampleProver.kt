package com.akakou.proverkit.example


import android.content.SharedPreferences
import android.net.Uri
import com.akakou.proverkit.AbstractProver

class Passing {}

class SampleProver(): AbstractProver<Passing>() {
    override suspend fun prove(
            uri: String,
            preferences: SharedPreferences,
            pass: Passing)  {
    }

    override suspend fun register(preferences: SharedPreferences) {
        val configEditor = preferences.edit()
        configEditor.putString("credential", "this is credential").apply()
    }
}