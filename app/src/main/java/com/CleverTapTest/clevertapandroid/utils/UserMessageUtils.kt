package com.CleverTapTest.clevertapandroid.utils

import android.content.Context
import android.widget.Toast

class UserMessageUtils {

    companion object {

        fun showToastMessage(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}
