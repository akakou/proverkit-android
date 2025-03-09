package com.akakou.proverkit.example

import android.net.Uri
import com.akakou.proverkit.AbstractProver

class SampleProver(uri: Uri): AbstractProver(uri) {
    override suspend fun needUserCheck() : Boolean {
        return true
    }

    override suspend fun prove() : String {
        return "this is proof"
    }
}