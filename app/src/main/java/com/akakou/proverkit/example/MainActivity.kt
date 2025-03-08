package com.akakou.proverkit.example

import android.content.Intent
import androidx.activity.ComponentActivity
import com.akakou.proverkit.CredentialViewActivity
import com.akakou.proverkit.IdentificationUtils
import com.akakou.proverkit.identification.phone_auth.PhoneNumberAuthActivity

class MainActivity : ComponentActivity() {
    override fun onResume() {
        super.onResume()

        val utils = IdentificationUtils(this@MainActivity)

        if (utils.hasIdentified()) {
            val intent = Intent(this@MainActivity, CredentialViewActivity::class.java)
            startActivity(intent)
        } else {
            utils.proveIdentity(PhoneNumberAuthActivity::class.java)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val intent = Intent(this@MainActivity, CredentialViewActivity::class.java)
        startActivity(intent)
    }
}
