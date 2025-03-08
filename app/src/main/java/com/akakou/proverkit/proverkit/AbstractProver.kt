package com.akakou.proverkit.proverkit

import android.net.Uri

abstract class AbstractProver {
    lateinit var uri: Uri
    fun init(uri: Uri) {
        this.uri = uri
    }

    open fun needUserCheck() : Boolean {
        return false
    }

    open fun prove() : String {
        return ""
    }
}