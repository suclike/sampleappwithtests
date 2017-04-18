package example.badoo.com.transactionviewer.test.custom

import android.support.test.espresso.Espresso

import android.os.Bundle

import android.support.test.runner.AndroidJUnitRunner

import custom.RxIdlingResource

class RxAndroidJUnitRunner : AndroidJUnitRunner() {
    override fun onCreate(arguments: Bundle) {
        super.onCreate(arguments)
        val rxIdlingResource = RxIdlingResource()
        Espresso.registerIdlingResources(rxIdlingResource)
    }
}