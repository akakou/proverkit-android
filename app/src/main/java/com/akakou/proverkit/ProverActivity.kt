package com.akakou.proverkit

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProverActivityHelper(
    manager: AbstractProverManager,
    val activity: ComponentActivity,
) {
    val prover: AbstractProver
    val callback: Uri
    var injectableUI : @Composable () -> Unit = { DefaultProverUI() }
    var scheme = "https"

    init{
        val intent = activity.intent
        val uri = Uri.parse(intent.dataString)
        val c = uri.getQueryParameter("callback")
        callback = Uri.parse(c)
        prover = manager.createProver(callback)!!
    }

    fun passProof() {
        GlobalScope.launch {
            val proof = prover.prove()
            val resultUrl = callback.buildUpon()
                .scheme(scheme)
                .fragment(proof)
                .build()

            GlobalScope.launch(Dispatchers.Main) {
                val browserIntent = Intent(Intent.ACTION_VIEW, resultUrl)
                activity.startActivity(browserIntent)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun setupUI() {
        GlobalScope.launch {
            GlobalScope.launch(Dispatchers.Main) {
                activity.setContent {
                    ProverActivityUI(
                        content = injectableUI,
                        onClick = { passProof() }
                    )
                }
            }
        }
    }
}

@Composable
fun ProverActivityUI(
    content: @Composable () -> Unit,
    onClick: () -> Unit
) {
    ProverKitTheme {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                content()
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        text = "Go !!",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Composable
fun DefaultProverUI() {
    val message = "Hi! Do you check it?"

    Text(
        text = message,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(8.dp)
    )
}
