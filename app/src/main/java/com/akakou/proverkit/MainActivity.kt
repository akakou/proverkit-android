package com.akakou.proverkit

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.akakou.proverkit.proverkit.AbstractProver
import com.akakou.proverkit.proverkit.MainActivityHelper
import com.akakou.proverkit.proverkit.identification.phone_auth.PhoneAuthActivity
import com.akakou.proverkit.proverkit.ui.theme.ProverKitTheme

class MainActivity : ComponentActivity() {
    val helper: MainActivityHelper =  MainActivityHelper(manager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this@MainActivity, PhoneAuthActivity::class.java)
        helper.proveIdentityIfNeeded(this@MainActivity, intent)

        enableEdgeToEdge()
        setContent {
            ProverKitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(
                        modifier = Modifier
                            .padding(innerPadding),
                        textAlign = TextAlign.Center,
                        text = "idToken",
                    )

                    Text(
                        modifier = Modifier
                            .padding(innerPadding),
                        textAlign = TextAlign.Center,
                        text = "idToken",
                    )
                }
            }
        }
    }
}
