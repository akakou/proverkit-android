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
    val manager: AbstractProverManager,
) {
    lateinit var callback: Uri

    @OptIn(DelicateCoroutinesApi::class)
    fun start(activity: ComponentActivity) {
        val intent = activity.intent
        val uri = Uri.parse(intent.dataString)
        val c = uri.getQueryParameter("callback")
        callback = Uri.parse(c)

        val prover = manager.createProver(callback)!!

        GlobalScope.launch {
            prover.prepare()
            val needUserCheck = prover.needUserCheck()

            if (!needUserCheck) {
                callbackWithProof(activity, prover).invoke()
                activity.finish()
            } else {
                GlobalScope.launch(Dispatchers.Main) {
                    activity.setContent {
                        ProverActivityUI(
                            content = { DefaultProverUI() },
                            onClick = callbackWithProof(activity, prover)
                        )
                    }
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun callbackWithProof(activity: ComponentActivity, prover: AbstractProver): () -> Unit {
        return {
            GlobalScope.launch {
                val proof = prover.prove()
                val resultUrl = callback.buildUpon()
                    .scheme("https")
                    .fragment(proof)
                    .build()

                GlobalScope.launch(Dispatchers.Main) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, resultUrl)
                    activity.startActivity(browserIntent)
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
