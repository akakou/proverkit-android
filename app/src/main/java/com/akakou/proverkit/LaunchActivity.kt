package com.akakou.proverkit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.akakou.proverkit.utils.hasIdentified
import com.akakou.proverkit.utils.hasRegistered
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.jvm.java

open class LaunchActivity<T: Any>(val prover: AbstractProver<T>, var identificationActivity: Class<*>) : ComponentActivity() {
    lateinit var config: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        config = getSharedPreferences("default", Context.MODE_PRIVATE)

        GlobalScope.launch {
            refresh()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        GlobalScope.launch {
            refresh()
        }
    }

     fun refresh() {
        var intent : Intent
        if (!hasIdentified(this)) {
            intent = Intent(this, identificationActivity)
            startActivity(intent)
            return
        }

        GlobalScope.launch {
            if (!hasRegistered(this@LaunchActivity)) {
                prover.register(config)
            }
            runOnUiThread {
                intent = Intent(this@LaunchActivity, CredentialViewActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
