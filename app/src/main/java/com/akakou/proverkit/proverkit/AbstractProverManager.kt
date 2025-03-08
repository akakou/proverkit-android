package com.akakou.proverkit.proverkit

import android.net.Uri

abstract class AbstractProverManager {
    open fun createProver(uri: Uri) : AbstractProver? {
        return null
    }

    open fun register() {
        return
    }
}