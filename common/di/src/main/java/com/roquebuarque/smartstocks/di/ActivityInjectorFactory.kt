package com.roquebuarque.smartstocks.di

import android.app.Activity
import dagger.MembersInjector
import javax.inject.Inject
import javax.inject.Provider

class ActivityInjectorFactory @Inject constructor(
/*    private val activityMap: Map<Class<out Activity>,
            @JvmSuppressWildcards Provider<MembersInjector<Activity>>>*/
) : ActivityInjector {

    override fun inject(activity: Activity) {

        TODO("Not yet implemented")
    }
}

interface Injector /*: MembersInjector<T>*/