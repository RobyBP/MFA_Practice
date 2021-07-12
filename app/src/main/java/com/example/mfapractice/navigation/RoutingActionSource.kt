package com.example.mfapractice.navigation

interface RoutingActionSource {

    fun registerActiveConsumer(consumer: RoutingActionConsumer)

    fun deregisterConsumer()

    fun dispatch(routingAction: (Router) -> Unit)
}
