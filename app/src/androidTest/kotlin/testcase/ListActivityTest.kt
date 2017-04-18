package testcase

import android.support.test.espresso.matcher.ViewMatchers.withId

import android.support.test.rule.ActivityTestRule

import android.support.test.runner.AndroidJUnit4

import custom.rule.RxIdlingRule

import example.badoo.com.transactionviewer.R

import example.badoo.com.transactionviewer.ui.TransactionListActivity

import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestRule

import org.junit.runner.RunWith

import robot.TransactionListRobot

@RunWith(AndroidJUnit4::class)
class ListActivityTest {

    val transactionListRobot: TransactionListRobot = TransactionListRobot()

    @Rule @JvmField
    val activity = ActivityTestRule<TransactionListActivity>(TransactionListActivity::class.java)

    @Rule @JvmField
    val rules: TestRule = RuleChain.outerRule(RxIdlingRule())

    @Test
    fun recycleView_exists() {
        val rv = withId(R.id.list_recycler_view)
        transactionListRobot.isViewDisplayed(rv)
    }

    @Test
    fun recycleView_has_expectedAmountOfItems() {
        val rv = withId(R.id.list_recycler_view)
        transactionListRobot.contains(rv, 12)
    }

    @Test
    fun recycleView_can_clickOnSpecifiedItem() {
        val rv = withId(R.id.list_recycler_view)
        transactionListRobot.clickOn(rv, 5)
        transactionListRobot.isViewDisplayed(rv)
        transactionListRobot.contains(rv, 429)
    }
}