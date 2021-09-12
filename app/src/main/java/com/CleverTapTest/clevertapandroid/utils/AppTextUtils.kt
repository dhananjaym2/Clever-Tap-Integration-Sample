package com.CleverTapTest.clevertapandroid.utils

import android.util.Patterns

class AppTextUtils {

    companion object {

        /**
         * Checks if provided email address is valid or not
         */
        fun isEmailValid(email: CharSequence): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}
