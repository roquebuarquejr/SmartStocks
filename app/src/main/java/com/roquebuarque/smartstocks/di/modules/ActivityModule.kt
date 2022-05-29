package com.roquebuarque.smartstocks.di.modules

import com.roquebuarque.smartstocks.di.components.ActivityComponent
import dagger.Module

@Module(
    subcomponents = [ActivityComponent::class],
    includes = [
        FeatureModule::class,
        ActivityInjectorModule::class,
    ]
)
object ActivityModule