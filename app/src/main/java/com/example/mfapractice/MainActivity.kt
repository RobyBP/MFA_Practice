package com.example.mfapractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mfapractice.navigation.Router
import com.example.mfapractice.navigation.RoutingActionConsumer
import com.example.mfapractice.navigation.RoutingActionSource
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), RoutingActionConsumer {

    private val mediator: RoutingActionSource by inject()
    private val router: Router by inject (parameters = { parametersOf(this) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediator.dispatch (Router::goToLoginScreen)
    }

    override fun onRoutingAction(routingAction: (Router) -> Unit) = routingAction(router)

    override fun onStart() {
        super.onStart()
        mediator.registerActiveConsumer(this)
    }

    override fun onStop() {
        mediator.deregisterConsumer()
        super.onStop()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mediator.dispatch(Router::goBack)
    }
}