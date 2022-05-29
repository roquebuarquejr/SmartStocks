package com.roquebuarque.smartstocks.di.contributor

import android.app.Activity
import com.roquebuarque.smartstocks.MainActivity
import com.roquebuarque.smartstocks.di.key.ActivityKey
import com.roquebuarque.smartstocks.di.injector.ActivityMemberInjector
import com.roquebuarque.smartstocks.stocks.presentation.ui.StockListActivity
import dagger.MembersInjector
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Suppress("UNCHECKED_CAST")
@Module
class ActivityContributorModule {

    @Provides
    @IntoMap
    @ActivityKey(MainActivity::class)
    fun providesMainActivityMemberInject(
        stockListActivityMemberInjector: MembersInjector<MainActivity>
    ): ActivityMemberInjector<Activity> {
        return ActivityMemberInjector(stockListActivityMemberInjector as MembersInjector<Activity>)
    }

    @Provides
    @IntoMap
    @ActivityKey(StockListActivity::class)
    fun providesStockListActivityMemberInject(
        stockListActivityMemberInjector: MembersInjector<StockListActivity>
    ): ActivityMemberInjector<Activity> {
        return ActivityMemberInjector(stockListActivityMemberInjector as MembersInjector<Activity>)
    }
}