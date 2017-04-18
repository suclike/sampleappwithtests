package custom

import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

import android.support.v7.widget.RecyclerView

import android.view.View

class RecyclerViewItemsCountMatcher private constructor(private val expectedItemCount: Int) : BaseMatcher<View>() {
    private var foundItems: Int = 0

    override fun matches(item: Any): Boolean {
        val recyclerView = item as RecyclerView
        foundItems = recyclerView.adapter.itemCount
        return foundItems == expectedItemCount
    }

    override fun describeTo(description: Description) {
        description.appendText("recycler view does not contains $expectedItemCount items. Found: $foundItems")
    }

    companion object {

        fun recyclerViewHasItemCount(itemCount: Int): Matcher<View> {
            return RecyclerViewItemsCountMatcher(itemCount)
        }
    }
}
