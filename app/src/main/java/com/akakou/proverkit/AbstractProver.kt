package com.akakou.proverkit

import android.content.SharedPreferences
import android.net.Uri


abstract class AbstractProver<T: Any>() {
    open suspend fun prove(uri: Uri, preferences: SharedPreferences, t: T) {
    }

    open suspend fun register(preferences: SharedPreferences) {
    }

    open suspend fun update(preferences: SharedPreferences) {
    }
}
