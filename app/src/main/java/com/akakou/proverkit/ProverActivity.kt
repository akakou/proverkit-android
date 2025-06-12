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
    var scheme = "https"
    var proofQuery = "proof"

    lateinit var callback: Uri
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferences = getSharedPreferences("default", Context.MODE_PRIVATE)

        val uri = intent.dataString!!.toUri()
        val c = uri.getQueryParameter("callback")
        callback = c!!.toUri()
    }

    fun run(t: T) {
        GlobalScope.launch {
            val proof = prover.prove(callback, preferences, t)
            val resultUrl = callback.buildUpon()
                .scheme(scheme)
                .appendQueryParameter(proofQuery, proof)
                .build()

            runOnUiThread {
                val browserIntent = Intent(Intent.ACTION_VIEW, resultUrl)
                startActivity(browserIntent)
            }
        }
    }
}

