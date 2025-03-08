package com.akakou.proverkit

import android.net.Uri

abstract class AbstractProver(val uri: Uri) {
    open fun needUserCheck() : Boolean {
        return false
    }

    open fun prove() : String {
        return ""
    }
}
