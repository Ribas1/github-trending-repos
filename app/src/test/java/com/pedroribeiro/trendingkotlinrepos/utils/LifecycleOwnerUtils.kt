package com.pedroribeiro.trendingkotlinrepos.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import io.mockk.every

object LifecycleOwnerUtils {
    /**
     * Setup the lifecycle owner that defines a mock lifecycle return to prevent LiveData crash on
     * observe function (LiveData.java : 165)
     */
    fun setupLifecycleOwner(lifecycleOwner: LifecycleOwner) {
        val lifecycle = LifecycleRegistry(lifecycleOwner)
        every { lifecycleOwner.lifecycle } returns lifecycle
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }
}
