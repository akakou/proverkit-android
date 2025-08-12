package com.akakou.proverkit.utils

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

fun createQR(string : String) : Bitmap? {
    val barcodeEncoder = BarcodeEncoder()
    val bitmap = barcodeEncoder.encodeBitmap(string, BarcodeFormat.QR_CODE, 1200, 1200)
    return bitmap
}
