package com.akakou.proverkit

import android.net.Uri

abstract class AbstractProver(val uri: Uri) {
    open suspend fun prove() : String {
        return ""
    }
}
