package com.akakou.proverkit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.akakou.proverkit.ui.theme.ProverKitTheme

object Message {
    val warnMessage = "Hi! Do you check it?"
    val submitButtonText = "Go !!"
}

class ProverActivity : ComponentActivity() {
    lateinit var callback: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val uri = Uri.parse(intent.dataString)
        val c = uri.getQueryParameter("callback")
        callback = Uri.parse(c)

        val needUserCheck = true

        if (!needUserCheck) {
            callbackWithProof()
            finish()
        }

        setContent {
            ProverActivityUI {
                callbackWithProof()
            }
        }
    }

    fun callbackWithProof() : Int {
        val proof = "this is proof"

        val resultUrl = callback.buildUpon()
            .scheme("https")
            .fragment(proof)
            .build()

        val browserIntent = Intent(Intent.ACTION_VIEW, resultUrl)
        startActivity(browserIntent)

        return 0
    }
}


@Composable
fun ProverActivityUI(callback: () -> Unit) {
    ProverKitTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = Message.warnMessage,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = callback,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        text = Message.submitButtonText,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}
