package com.akakou.proverkit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.akakou.proverkit.proverkit.identification.phone_auth.PhoneAuthActivity
import com.akakou.proverkit.ui.theme.ProverKitTheme

class MainActivity : ComponentActivity() {
    lateinit var config : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        config = getSharedPreferences("default", Context.MODE_PRIVATE)

        val idToken = config.getString("idToken", "")!!
        if (idToken == "") {
            val intent = Intent(this@MainActivity, PhoneAuthActivity::class.java)
            startActivity(intent)
        }

        enableEdgeToEdge()
        setContent {
            ProverKitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(
                        modifier = Modifier
                            .padding(innerPadding),
                        textAlign = TextAlign.Center,
                        text = idToken,
                    )
                }
            }
        }
    }
}
