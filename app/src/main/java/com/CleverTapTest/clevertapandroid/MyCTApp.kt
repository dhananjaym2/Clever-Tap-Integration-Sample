package com.CleverTapTest.clevertapandroid

import android.app.Application
import com.clevertap.android.sdk.CleverTapAPI


class MyCTApp : Application() {
//    var cleverTapDefaultInstance: CleverTapAPI? = null

    companion object {
        var cleverTapDefaultInstance: CleverTapAPI? = null
    }

    override fun onCreate() {
        super.onCreate()
        cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(
            applicationContext
        )
    }
}