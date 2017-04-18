package testcase

import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import custom.RxIdlingResource

import example.badoo.com.transactionviewer.R

import example.badoo.com.transactionviewer.ui.TransactionListActivity

import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith

import robot.TransactionListRobot

@RunWith(AndroidJUnit4::class)
class ListActivityTest {

    val transactionListRobot: TransactionListRobot = TransactionListRobot()
    private val rxIdlingResource = RxIdlingResource() //todo not working

    @Rule @JvmField
    val activity = ActivityTestRule<TransactionListActivity>(TransactionListActivity::class.java)
    
    @Test
    fun recycleView_exists() {
        val rv = withId(R.id.list_recycler_view)
        transactionListRobot.isViewDisplayed(rv)
    }

    @Test
    fun recycleView_has_expectedAmountOfItems() {
        val rv = withId(R.id.list_recycler_view)
        Thread.sleep(1000); // need while idling isn't working
        transactionListRobot.contains(rv, 12)
    }

    @Test
    fun recycleView_can_clickOnSpecifiedItem() {
        val rv = withId(R.id.list_recycler_view)
        Thread.sleep(1000);
        transactionListRobot.clickOn(rv, 5)
        transactionListRobot.isViewDisplayed(rv)
        Thread.sleep(1000);
        transactionListRobot.contains(rv, 429)
    }
}