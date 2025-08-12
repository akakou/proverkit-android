package com.akakou.proverkit.example
import android.os.Bundle
import com.akakou.proverkit.ProverActivity


class SampleProverActivity : ProverActivity<Passing>(SampleProver()) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pass = Passing()
        run(pass)
    }
}


