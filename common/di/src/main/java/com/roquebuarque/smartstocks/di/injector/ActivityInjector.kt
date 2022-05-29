package com.roquebuarque.smartstocks.di.injector

import android.app.Activity

interface ActivityInjector {
    fun inject(activity: Activity)
}