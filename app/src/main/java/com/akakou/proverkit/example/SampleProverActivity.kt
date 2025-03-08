package com.akakou.proverkit.example
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.akakou.proverkit.ProverActivityHelper


@SuppressLint("MissingSuperCall")
class SampleProverActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val helper = ProverActivityHelper(manager)
        helper.start(this@SampleProverActivity)
    }
}


