package com.roquebuarque.smartstocks.stocks

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.mock
import com.roquebuarque.smartstocks.network.Resource
import com.roquebuarque.smartstocks.stocks.domain.models.ConnectionFailedException
import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import com.roquebuarque.smartstocks.stocks.domain.provider.StockRepository
import com.roquebuarque.smartstocks.stocks.presentation.StockListState
import com.roquebuarque.smartstocks.stocks.presentation.StockListViewModel
import com.roquebuarque.smartstocks.stocks.presentation.asUI
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StockListViewModelTest {

    private val repo: StockRepository = mock()
    private val sut = StockListViewModel(repo)

    @Test
    fun `when launch should trigger loading state`(){
        //Given
        repo.getStockList() willReturn Flowable.empty()

        //When
        val state = sut.state.test()

        //Then
        state.assertValue(StockListState())
    }

    @Test
    fun `when fetch should return content sync state`(){
        //Given
        val stocksDtoList = listOf(StockDto("US90353T1007", 0.0))
        repo.getStockList() willReturn Flowable.just(Resource.success(stocksDtoList))

        //When
        val state = sut.state.test()

        //Then
        val expected = StockListState(stocks = stocksDtoList.asUI(), syncState = StockListState.SyncState.Content)
        val result = state.values().last()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `when fetch should return empty sync state`(){
        //Given
        repo.getStockList() willReturn Flowable.just(Resource.success(emptyList()))

        //When
        val state = sut.state.test()

        //Then
        val expected = StockListState(syncState = StockListState.SyncState.Empty)
        val result = state.values().last()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `when fetch should return error sync state`(){
        //Given
        repo.getStockList() willReturn Flowable.just(Resource.error(ConnectionFailedException("")))

        //When
        val state = sut.state.test()

        //Then
        val expected = StockListState(syncState = StockListState.SyncState.Error(""))
        val result = state.values().last()
        assertThat(result).isEqualTo(expected)
    }

}