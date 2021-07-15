package com.roquebuarque.smartstocks.stocks

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.roquebuarque.smartstocks.network.Resource
import com.roquebuarque.smartstocks.stocks.domain.facade.StockFacadeImpl
import com.roquebuarque.smartstocks.stocks.domain.models.ConnectionFailedException
import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import com.roquebuarque.smartstocks.stocks.domain.models.SupportedStocks
import com.roquebuarque.smartstocks.stocks.domain.provider.StockFacade
import com.roquebuarque.smartstocks.stocks.domain.provider.StockLocal
import com.roquebuarque.smartstocks.stocks.domain.provider.StockService
import com.tinder.scarlet.Message
import com.tinder.scarlet.ShutdownReason
import com.tinder.scarlet.WebSocket
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StockFacadeImplTest {

    private val remote: StockService = mock()
    private val local: StockLocal = mock()
    private val scheduler = TestScheduler()

    private val sut: StockFacade = StockFacadeImpl(remote, local, Gson(), scheduler)

    @Before
    fun setup(){
        local
            .retrieve() willReturn Observable.empty<Resource<List<StockDto>>>()
    }

    @Test
    fun `when connect should subscribe`() {
        //Given
        val open = WebSocket.Event.OnConnectionOpened(Any())
        remote.observeWebSocketEvent() willReturn Flowable.just(open)

        //When
        sut.fetchAllStocks().test()

        //Then
        remote.verify(SupportedStocks.values().size) { sendSubscribe(any()) }
    }

    @Test
    fun `when receive new message should save in local`() {
        //Given
        val stockDto = StockDto("US0378331005", 0.0)
        val message: String = Gson().toJson(stockDto)
        val text = WebSocket.Event.OnMessageReceived(Message.Text(message))
        remote.observeWebSocketEvent() willReturn Flowable.just(text)

        //When
        sut.fetchAllStocks().test()

        //Then
        local.verifyOnce { save(stockDto) }
    }

    @Test
    fun `closing connection should throw an error`() {
        //Given
        val closing = WebSocket.Event.OnConnectionClosing(ShutdownReason(10, "Closing"))
        remote.observeWebSocketEvent() willReturn Flowable.just(closing)

        //When
        val result = sut.fetchAllStocks().test()

        //Then
        assertThat(result.errors().last().message).isEqualTo("Closing")
    }

    @Test
    fun `closed connection should throw an error`() {
        //Given
        val closing = WebSocket.Event.OnConnectionClosed(ShutdownReason(10, "Closed"))
        remote.observeWebSocketEvent() willReturn Flowable.just(closing)

        //When
        val result = sut.fetchAllStocks().test()

        //Then
        assertThat(result.errors().last().message).isEqualTo("Closed")
    }

    @Test
    fun `OnConnectionFailed should throw an error`() {
        //Given
        val closing = WebSocket.Event.OnConnectionFailed(ConnectionFailedException("Failed"))
        remote.observeWebSocketEvent() willReturn Flowable.just(closing)

        //When
        val result = sut.fetchAllStocks().test()

        //Then
        assertThat(result.errors().last().message).isEqualTo("Failed")
    }

}