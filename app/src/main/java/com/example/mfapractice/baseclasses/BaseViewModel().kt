package com.example.mfapractice.baseclasses

import androidx.lifecycle.ViewModel
import com.example.mfapractice.navigation.Router
import com.example.mfapractice.navigation.RoutingActionSource

abstract class BaseViewModel(val mediator: RoutingActionSource): ViewModel() {
    fun goBack() {
        mediator.dispatch(Router::goBack)
    }
}