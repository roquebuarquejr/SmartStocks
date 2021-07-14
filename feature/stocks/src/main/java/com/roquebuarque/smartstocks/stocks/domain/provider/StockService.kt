package com.roquebuarque.smartstocks.stocks.domain.provider

import com.roquebuarque.smartstocks.network.Message
import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface StockService {
    @Receive
    fun observeWebSocketEvent(): Flowable<WebSocket.Event>
    @Send
    fun sendSubscribe(message: Message)
    @Receive
    fun observeStocks(): Flowable<StockDto>
}