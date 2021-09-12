package com.CleverTapTest.clevertapandroid

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.CleverTapTest.clevertapandroid.utils.AppTextUtils
import com.CleverTapTest.clevertapandroid.utils.UserMessageUtils
import com.clevertap.android.sdk.CleverTapAPI

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var logProductViewedEventButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var logUserInfoButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        logProductViewedEventButton = findViewById(R.id.logProductViewedEventButton)
        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        logUserInfoButton = findViewById(R.id.logUserInfoButton)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        logProductViewedEventButton.setOnClickListener(this)
        logUserInfoButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.logProductViewedEventButton -> {
                logProductViewedEvent()
            }

            R.id.logUserInfoButton -> {
                logUserInfo()
            }

            R.id.enablePushNotificationsButton -> {
                enablePushNotifications()
            }
        }
    }

    private fun logUserInfo() {
        val name = nameEditText.text.trim()
        if (name.isBlank()) {
            showErrorMessage(getString(R.string.emptyName))
            return
        }

        val email = nameEditText.text.trim()
        if (email.isBlank()) {
            showErrorMessage(getString(R.string.emptyEmail))
            return
        }

        if (!AppTextUtils.isEmailValid(email)) {
            showErrorMessage(getString(R.string.invalidEmail))
            return
        }

        val userInfo = HashMap<String, Any>()
        userInfo["Name"] = name
        userInfo["Email address"] = email
        CleverTapAPI.getDefaultInstance(applicationContext)?.pushEvent("logUserInfo", userInfo)
    }

    private fun logProductViewedEvent() {
        val eventActions = HashMap<String, Any>()
        eventActions["Product ID"] = 1
        eventActions["Product Image"] =
            "https://d35fo82fjcw0y8.cloudfront.net/2018/07/26020307/customer-success-clevertap.jpg"
        eventActions["Product Name"] = "CleverTap"
        CleverTapAPI.getDefaultInstance(applicationContext)
            ?.pushEvent("Product viewed", eventActions)
    }

    private fun showErrorMessage(message: String) {
        UserMessageUtils.showToastMessage(this, message)
    }

    private fun enablePushNotifications() {
        val profileUpdate = HashMap<String, Any>()
        profileUpdate["MSG-push"] = true // Enable push notifications
        CleverTapAPI.getDefaultInstance(applicationContext)?.onUserLogin(profileUpdate)
    }
}