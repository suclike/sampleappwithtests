package custom.idling


import java.util.concurrent.atomic.AtomicInteger

import android.support.test.espresso.IdlingResource

import android.util.Log

class RxIdlingResource : IdlingResource {

    private val subscriptionsCount = AtomicInteger(0)
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return javaClass.simpleName
    }

    override fun isIdleNow(): Boolean {
        return subscriptionsCount.get() == 0
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        resourceCallback = callback
    }

    fun incrementActiveSubscriptionsCount() {
        val count = subscriptionsCount.incrementAndGet()
        Log.i(TAG, "Active subscriptions count increased to " + count)
    }

    fun decrementActiveSubscriptionsCount() {
        val count = subscriptionsCount.decrementAndGet()
        Log.i(TAG, "Active subscriptions count decreased to " + count)
        if (isIdleNow && resourceCallback != null) {
            Log.i(TAG, "There is no active subscriptions, transitioning to Idle")
            resourceCallback!!.onTransitionToIdle()
        }
    }

    companion object {
        private val TAG = "RxIdlingResource"
    }
}