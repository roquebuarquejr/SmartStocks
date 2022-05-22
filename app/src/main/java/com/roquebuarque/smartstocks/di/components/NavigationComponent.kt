package com.roquebuarque.smartstocks.di.components

import com.roquebuarque.smartstocks.MainActivity
import com.roquebuarque.smartstocks.di.modules.NavigationModule
import dagger.Subcomponent

@Subcomponent
interface NavigationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): NavigationComponent
    }

    fun inject(mainActivity: MainActivity)
}