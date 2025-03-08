package com.akakou.proverkit.example

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.akakou.proverkit.MainActivityHelper
import com.akakou.proverkit.identification.phone_auth.PhoneAuthActivity

class MainActivity : ComponentActivity() {
    val helper: MainActivityHelper =  MainActivityHelper(manager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this@MainActivity, PhoneAuthActivity::class.java)
        helper.proveIdentityIfNeeded(this@MainActivity, intent)

        helper.showCredentialUI(this@MainActivity)
    }
}
