package custom.runner

import android.support.test.espresso.Espresso

import android.os.Bundle

import android.support.test.runner.AndroidJUnitRunner

import custom.idling.RxIdlingResource

/**
 * TODO fix runner, just an example
 * to run tests - 
 * adb shell am instrument -w -r -e debug false -e class testcase.ListActivityTest example.badoo.com.transactionviewer.test/custom.runner.RxAndroidJUnitRunner
 */
class RxAndroidJUnitRunner : AndroidJUnitRunner() {
    override fun onCreate(arguments: Bundle) {
        super.onCreate(arguments)
        val rxIdlingResource = RxIdlingResource()
        Espresso.registerIdlingResources(rxIdlingResource)
    }
}