package com.akakou.proverkit.example

import android.net.Uri
import com.akakou.proverkit.AbstractProverManager

val manager = SampleProverManager()

class SampleProverManager: AbstractProverManager() {
    override fun createProver(uri: Uri) : SampleProver {
        return SampleProver(uri)
    }

    override suspend fun register()  {
        return
    }
}