package com.akakou.proverkit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import com.akakou.proverkit.utils.hasIdentified
import com.akakou.proverkit.utils.hasRegistered
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class LaunchActivity<T : Any>(
    val prover: AbstractProver<T>,
    var identificationActivity: Class<*>
) : ComponentActivity() {

    lateinit var config: SharedPreferences

    override fun onResume() {
        super.onResume()
        config = getSharedPreferences("default", Context.MODE_PRIVATE)

        if (!hasIdentified(this)) {
            val intent = Intent(this, identificationActivity)
            startActivity(intent)
            return
        }

        GlobalScope.launch {
            if (!hasRegistered(this@LaunchActivity)) {
                prover.register(this@LaunchActivity)
            }

            runOnUiThread {
                val intent = Intent(this@LaunchActivity, CredentialViewActivity::class.java)
                startActivity(intent)
            }
        }
    }
}