package com.akakou.proverkit

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri


abstract class AbstractProver<T: Any>() {
    open suspend fun prove(uri: String, context: Context, t: T) {
    }

    open suspend fun register(context: Context) {
    }
}
