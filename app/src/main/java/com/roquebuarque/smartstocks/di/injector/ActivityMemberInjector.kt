package com.roquebuarque.smartstocks.di.injector

import android.app.Activity
import dagger.MembersInjector

class ActivityMemberInjector<T : Activity>(
    private val activityMemberInjector: MembersInjector<Activity>
) {

    fun inject(activity: T) {
        activityMemberInjector.injectMembers(activity)
    }
}