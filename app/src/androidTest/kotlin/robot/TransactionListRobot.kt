package robot

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.v7.widget.RecyclerView

import android.view.View
import custom.matcher.RecyclerViewItemsCountMatcher

import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class TransactionListRobot {
    
    fun isViewDisplayed(viewMatcher: Matcher<View>): TransactionListRobot {
        onView(viewMatcher).check(matches(isDisplayed()))
        return this
    }

    fun contains(viewMatcher: Matcher<View>, amount: Int): TransactionListRobot {
        onView(viewMatcher)
                .check(matches(RecyclerViewItemsCountMatcher.recyclerViewHasItemCount(amount)))
        
        return this
    }

    fun clickOn(viewMatcher: Matcher<View>, item: Int): TransactionListRobot {
        onView(allOf<View>(viewMatcher, isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(item, click()));

        return this
    }
}
