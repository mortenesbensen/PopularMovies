package com.mortenesbensen.popularmovis.util

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class LiveEvent<T> : MutableLiveData<T>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasObservers()) {
            throw Throwable("Only one observer can observe LiveEvents")
        }

        super.observe(owner, Observer { data ->

            if (shouldDisregardValue(data)) {
                return@Observer
            }

            observer.onChanged(data)

            value = null
        })
    }

    private fun shouldDisregardValue(data: T): Boolean = data == null || !hasObservers()


    @MainThread
    fun sendEvent(event: T) {
        postValue(event)
    }
}