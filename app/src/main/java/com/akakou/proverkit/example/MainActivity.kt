package com.akakou.proverkit.example

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.akakou.proverkit.AbstractProver
import com.akakou.proverkit.identification.phone_auth.PhoneNumberAuthActivity
import com.akakou.proverkit.ui.theme.ProverKitTheme

class MainActivity : com.akakou.proverkit.LaunchActivity<Passing>(
    SampleProver(),
    PhoneNumberAuthActivity::class.java
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProverKitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    Text(
                        text = "Hello!",
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
