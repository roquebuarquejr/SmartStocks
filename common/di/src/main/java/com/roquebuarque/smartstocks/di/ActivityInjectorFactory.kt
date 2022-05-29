package com.roquebuarque.smartstocks.di

import android.app.Activity
import dagger.MembersInjector
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

class ActivityMemberInjector<T : Activity>(
    private val activityMemberInjector: MembersInjector<Activity>
) {

    fun inject(activity: T) {
        activityMemberInjector.injectMembers(activity)
    }
}