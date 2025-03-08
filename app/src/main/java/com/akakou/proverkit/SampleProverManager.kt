package com.akakou.proverkit

import android.net.Uri
import com.akakou.proverkit.proverkit.AbstractProver
import com.akakou.proverkit.proverkit.AbstractProverManager

val manager = SampleProverManager()

class SampleProverManager: AbstractProverManager() {
    override fun createProver(uri: Uri) : SampleProver {
        return SampleProver(uri)
    }

    override fun register()  {
        return
    }
}