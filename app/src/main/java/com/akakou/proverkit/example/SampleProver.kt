package com.akakou.proverkit.example

import android.net.Uri
import com.akakou.proverkit.AbstractProver

class SampleProver(uri: Uri): AbstractProver(uri) {
    override fun needUserCheck() : Boolean {
        return true
    }

    override fun prove() : String {
        return "this is proof"
    }
}