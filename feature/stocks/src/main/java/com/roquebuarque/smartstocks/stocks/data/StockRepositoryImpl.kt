package com.roquebuarque.smartstocks.stocks.data

import com.roquebuarque.smartstocks.di.scopes.ActivityScope
import com.roquebuarque.smartstocks.network.Resource
import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import com.roquebuarque.smartstocks.stocks.domain.provider.StockRepository
import io.reactivex.Flowable
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.random.Random

@ActivityScope
class StockRepositoryImpl @Inject constructor(val retrofit: Retrofit) : StockRepository {

    override fun getStockList(): Flowable<Resource<List<StockDto>>> {
        print("XABLAU $retrofit")
        return Flowable.just(Resource.success(mock()))
    }

    companion object {

        fun mock(): List<StockDto> {
            return listOf(
                StockDto("US0378331005", price = Random.nextDouble()),
                StockDto("DE000A1EWWW0", price = Random.nextDouble()),
                StockDto("DE0007100000", price = Random.nextDouble()),
                StockDto("US64110L1061", price = Random.nextDouble()),
                StockDto("US6541061031", price = Random.nextDouble()),
                StockDto("US0231351067", price = Random.nextDouble()),
                StockDto("DE0007236101", price = Random.nextDouble()),
                StockDto("DE000BAY0017", price = Random.nextDouble()),
                StockDto("DE0007664005", price = Random.nextDouble()),
                StockDto("US88160R1014", price = Random.nextDouble()),
                StockDto("NL0000009165", price = Random.nextDouble()),
                StockDto("XS1168962063", price = Random.nextDouble()),
                StockDto("US90353T1007", price = Random.nextDouble()),
                StockDto("US7960542030", price = Random.nextDouble())
            )
        }
    }
}