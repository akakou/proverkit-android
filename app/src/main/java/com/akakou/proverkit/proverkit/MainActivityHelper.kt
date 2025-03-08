package com.akakou.proverkit.proverkit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.akakou.proverkit.proverkit.ProverkitUtils.createQR
import com.akakou.proverkit.proverkit.identification.phone_auth.PhoneAuthActivity
import com.akakou.proverkit.proverkit.ui.theme.ProverKitTheme
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainActivityHelper(val manager: AbstractProverManager)
{
    lateinit var config : SharedPreferences
    var message = "Before using this app, you need to prove your identity first."

    fun proveIdentityIfNeeded(activity: ComponentActivity,  identificationIntent: Intent) {
        config = activity.getSharedPreferences("default", Context.MODE_PRIVATE)

        val idToken = config.getString("idToken", "")!!
        if (idToken == "") {
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
            proveIdentity(activity, identificationIntent)
        }
    }

    fun proveIdentity(activity: ComponentActivity, identificationIntent: Intent) {
        activity.startActivity(identificationIntent)
    }

    fun showCredentialUI(activity: ComponentActivity) {
        val bitmap = createCredentialQR(activity)!!
        activity.enableEdgeToEdge()
        activity.setContent {
            CredentialQRUI(bitmap)
        }
    }

    fun createCredentialQR(activity: Activity) : Bitmap? {
        val config = activity.getSharedPreferences("default", Context.MODE_PRIVATE)
        val idToken = config.getString("idToken", "")!!
        val bmp = createQR(idToken)

        return bmp
    }
}

@Composable
fun CredentialQRUI(bitmap: Bitmap) {
        return ProverKitTheme {
            Scaffold { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "ID Token",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Card(
                            shape = MaterialTheme.shapes.medium,
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "ID Token",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                            )
                        }
                    }
                }
            }
        }

}