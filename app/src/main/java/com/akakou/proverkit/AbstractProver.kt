package com.akakou.proverkit

import android.net.Uri

abstract class AbstractProver(val uri: Uri) {
    open suspend fun prepare() {
    }

    open suspend fun needUserCheck() : Boolean {
        return false
    }

    open suspend fun prove() : String {
        return ""
    }
}
