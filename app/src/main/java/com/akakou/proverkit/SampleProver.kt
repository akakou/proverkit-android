package com.akakou.proverkit

import com.akakou.proverkit.proverkit.AbstractProver

class SampleProver: AbstractProver() {
    override fun needUserCheck() : Boolean {
        return true
    }

    override fun prove() : String {
        return "this is proof"
    }
}