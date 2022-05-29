package com.roquebuarque.smartstocks.di

import android.app.Activity

interface ActivityInjector {
    fun inject(activity: Activity)
}