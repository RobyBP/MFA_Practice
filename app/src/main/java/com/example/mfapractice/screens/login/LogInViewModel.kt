package com.example.mfapractice.screens.login

import com.example.mfapractice.baseclasses.BaseViewModel
import com.example.mfapractice.navigation.RoutingActionSource

class LogInViewModel(mediator: RoutingActionSource): BaseViewModel(mediator) {

    fun goToOTP(phoneNumber: String) = mediator.dispatch { it.goToTOPScreen(phoneNumber) }

}
