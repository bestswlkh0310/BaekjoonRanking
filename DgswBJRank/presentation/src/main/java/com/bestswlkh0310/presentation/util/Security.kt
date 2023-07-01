package com.bestswlkh0310.presentation.util

import android.util.Log
import com.bestswlkh0310.presentation.util.Constant.TAAG
import java.security.MessageDigest

object Security {
    fun hashPassword(password: String): String {
        val messageDigest = MessageDigest.getInstance("SHA-256")
        val hashedBytes = messageDigest.digest(password.toByteArray())
        return bytesToHex(hashedBytes)
    }

    private fun bytesToHex(bytes: ByteArray): String {
        val hexChars = CharArray(bytes.size * 2)
        val hexArray = "0123456789ABCDEF".toCharArray()

        for (i in bytes.indices) {
            val v = bytes[i].toInt() and 0xFF
            hexChars[i * 2] = hexArray[v.ushr(4)]
            hexChars[i * 2 + 1] = hexArray[v and 0x0F]
        }
        Log.d(TAAG, "${String(hexChars).length} - bytesToHex() called")
        Log.d(TAAG, "${String(hexChars)} - bytesToHex() called")
        return String(hexChars)
    }
}