package com.roquebuarque.smartstocks.di.components

import com.roquebuarque.smartstocks.di.ActivityInjector
import com.roquebuarque.smartstocks.di.scopes.ActivityScope
import dagger.Subcomponent

@Subcomponent
interface ActivityComponent {

    fun getActivityInjector(): ActivityInjector

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }
}
