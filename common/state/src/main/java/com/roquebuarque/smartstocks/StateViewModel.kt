package com.roquebuarque.smartstocks

import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.Observable


/**
 * This is our base view model
 * if you need to handle events and state
 * you should extend this class and provide
 * what is your event class and your state class
 * and this will give you back a state observable
 * and an entry point to receive the events
 */
abstract class StateViewModel<Event, State> : ViewModel(){

    /**
     * This hold the current state
     * You should subscribe this in your view
     */
    abstract val state: Flowable<State>

    /**
     * An entry point of the events from the view
     */
    abstract fun dispatch(event: Event)
}