package com.roquebuarque.smartstocks.network

import com.tinder.scarlet.WebSocket
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SocketHandlerImpl @Inject constructor(private val service: SocketService): SocketHandler {

    override fun subscribe(message: Message){
        service.sendSubscribe(message)
    }

    override fun <T> whenConnected(func: () -> Flowable<T>): Flowable<T> {
        return service
            .observeWebSocketEvent()
            .filter { it is WebSocket.Event.OnConnectionOpened<*> }
            .flatMap {
                func()
            }
    }

}