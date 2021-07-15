package com.roquebuarque.smartstocks.stocks.domain.provider

import com.roquebuarque.smartstocks.network.Message
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

/**
 * Public interface for remote websockets service
 */
interface StockService {

    /**
     * Will react to service event state
     */
    @Receive
    fun observeWebSocketEvent(): Flowable<WebSocket.Event>

    /**
     * Will send message to websockets service
     */
    @Send
    fun sendSubscribe(message: Message)
}