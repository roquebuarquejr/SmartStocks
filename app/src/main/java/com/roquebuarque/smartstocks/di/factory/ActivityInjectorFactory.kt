package com.roquebuarque.smartstocks.di.factory


import android.app.Activity
import com.roquebuarque.smartstocks.di.injector.ActivityInjector
import com.roquebuarque.smartstocks.di.injector.ActivityMemberInjector
import javax.inject.Inject
import javax.inject.Provider

class ActivityInjectorFactory @Inject constructor(
    private val activityMap: Map<Class<out Activity>,
            @JvmSuppressWildcards Provider<ActivityMemberInjector<Activity>>>
) : ActivityInjector {

    override fun inject(activity: Activity) {
        val provider = activityMap[activity.javaClass]
        provider?.get()?.inject(activity)
    }

}