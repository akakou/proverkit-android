package com.akakou.proverkit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.core.net.toUri
import kotlin.reflect.KClass


open class ProverActivity<T: Any>(val prover: AbstractProver<T>) : ComponentActivity() {
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    fun run(t: T) {
        GlobalScope.launch {
            val uri = intent.dataString!!.toUri()
            val callback = uri.getQueryParameter("callback")

            prover.prove(callback!!, preferences, t)
            runOnUiThread {
                finish()
            }
        }
    }
}

