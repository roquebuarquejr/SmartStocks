package com.roquebuarque.smartstocks.network

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface SocketService {

    @Receive
    fun observeWebSocketEvent(): Flowable<WebSocket.Event>

    @Send
    fun sendSubscribe(message: Message)
}