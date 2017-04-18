package custom.rule

import android.support.test.espresso.Espresso

import custom.idling.RxIdlingResource

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

import rx.functions.Action0
import rx.functions.Func1

import rx.plugins.RxJavaHooks

class RxIdlingRule : TestRule {

    private val rxIdlingResource = RxIdlingResource()

    private val ACTIONS_COUNTER = Func1<Action0, Action0> { action ->
        rxIdlingResource.incrementActiveSubscriptionsCount()
        Action0 {
            try {
                action.call()
            } finally {
                rxIdlingResource.decrementActiveSubscriptionsCount()
            }
        }
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {

                Espresso.registerIdlingResources(rxIdlingResource)
                RxJavaHooks.setOnScheduleAction(ACTIONS_COUNTER)
                
                base.evaluate()

                RxJavaHooks.setOnScheduleAction(null)
                Espresso.unregisterIdlingResources(rxIdlingResource)
            }
        }
    }
}