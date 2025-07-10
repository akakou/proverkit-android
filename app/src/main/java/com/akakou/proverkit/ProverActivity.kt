package com.akakou.proverkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.core.net.toUri


open class ProverActivity<T: Any>(val prover: AbstractProver<T>) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun run(t: T) {
        GlobalScope.launch {
            val uri = intent.dataString!!.toUri()
            val callback = uri.getQueryParameter("callback")

            prover.prove(callback!!, this@ProverActivity, t)
            runOnUiThread {
                finish()
            }
        }
    }
}

