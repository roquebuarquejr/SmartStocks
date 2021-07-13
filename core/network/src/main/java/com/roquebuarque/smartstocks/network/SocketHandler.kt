package com.roquebuarque.smartstocks.network

import io.reactivex.Flowable

interface SocketHandler {

    fun subscribe(message: Message)

    fun <T> whenConnected(func: () -> Flowable<T>): Flowable<T>
}