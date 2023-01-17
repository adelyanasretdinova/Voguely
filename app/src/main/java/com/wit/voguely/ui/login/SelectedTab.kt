package com.wit.voguely.ui.login

import com.wit.voguely.R

enum class SelectedTab(
    val welcomeMessage :Int,
    val buttonText: Int
) {
    LOGIN(
        welcomeMessage = R.string.welcome_back,
        buttonText = R.string.login
    ),
    SIGN_UP(
        welcomeMessage = R.string.join_now,
        buttonText = R.string.signup
    )
}