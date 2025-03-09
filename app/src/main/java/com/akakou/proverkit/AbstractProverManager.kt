package com.akakou.proverkit

import android.net.Uri

abstract class AbstractProverManager {
    open fun createProver(uri: Uri) : AbstractProver? {
        return null
    }

    open suspend fun register() {
        return
    }
}