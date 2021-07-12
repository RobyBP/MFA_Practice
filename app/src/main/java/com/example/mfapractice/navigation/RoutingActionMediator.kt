package com.example.mfapractice.navigation

import java.util.*

class RoutingActionMediator : RoutingActionSource {

    private var activeActionConsumer: RoutingActionConsumer? = null
    private val actionQueue: Queue<(Router) -> Unit> = LinkedList()

    override fun registerActiveConsumer(consumer: RoutingActionConsumer) {
        activeActionConsumer = consumer
        flushQueue()
    }

    override fun deregisterConsumer() {
        activeActionConsumer = null
    }

    override fun dispatch(routingAction: (Router) -> Unit) {
        activeActionConsumer?.onRoutingAction(routingAction) ?: actionQueue.add(routingAction)
    }

    private fun flushQueue() {
        while (!actionQueue.isEmpty()) {
            activeActionConsumer!!.onRoutingAction(actionQueue.poll()!!)
        }
    }
}