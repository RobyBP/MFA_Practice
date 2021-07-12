package com.example.mfapractice.navigation

interface Router {

    fun goToLoginScreen()

    fun goToTOPScreen(phoneNumber: String)

    fun goToHomeScreen()

    fun goBack()
}
