package com.akakou.proverkit.proverkit.identification.phone_auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.akakou.proverkit.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class PhoneAuthActivity : ComponentActivity() {
    lateinit var configEditor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val config = getSharedPreferences("default", Context.MODE_PRIVATE)
        configEditor = config.edit()

        val providers = arrayListOf(
            AuthUI.IdpConfig.PhoneBuilder().build(),
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setTheme(R.style.Theme_ProverKit)
            .setAvailableProviders(providers)
            .build()

        signInLauncher.launch(signInIntent)
    }

    @OptIn(DelicateCoroutinesApi::class)
    val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        if (res.resultCode != RESULT_OK) {
            Toast.makeText(this@PhoneAuthActivity, "Sign in failed", Toast.LENGTH_LONG).show()
        }

        val user = FirebaseAuth.getInstance().currentUser
        GlobalScope.launch{
            val task = user?.getIdToken(true)?.await()
            val idToken = task?.token

            if (idToken == null) {
                Toast.makeText(this@PhoneAuthActivity, "Sign in failed", Toast.LENGTH_LONG).show()
            }

            GlobalScope.launch(Dispatchers.Main){
                configEditor.putString("idToken", idToken).apply()

                finish()
            }
        }
    }
}
