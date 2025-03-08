package com.akakou.proverkit
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.akakou.proverkit.proverkit.ProverActivityHelper


@SuppressLint("MissingSuperCall")
class SampleProverActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prover = SampleProver()

        val helper = ProverActivityHelper(
            prover = prover
        )

        helper.start(this@SampleProverActivity)
    }
}


