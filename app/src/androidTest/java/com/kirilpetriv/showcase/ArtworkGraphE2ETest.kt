package com.kirilpetriv.showcase

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import okhttp3.internal.wait
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class ArtworkGraphE2ETest {
    @get:Rule
    val composeActivityTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testArtworkListScreen() {
        with(composeActivityTestRule) {
            waitForIdle()
            waitUntilAtLeastOneExists(hasTestTag("artwork_list"))
            onNodeWithTag("artwork_list").assertExists()
            onAllNodesWithTag("artwork_card")[0].assertExists().assertIsDisplayed()
            with(onAllNodesWithTag("artwork_card")[0].onChild()) {
                hasTestTag("artwork_thumbnail")
                hasTestTag("artwork_title")
                hasTestTag("artwork_artist")
            }
        }
    }

    @Test
    fun testArtworkCardNavigationAndDetail() {
        with(composeActivityTestRule) {
            waitForIdle()
            waitUntilAtLeastOneExists(hasTestTag("artwork_list"))
            // set up to make sure a card is visible with which we can navigate to detail
            onAllNodesWithTag("artwork_card")[0].assertExists().performClick()
            // check if the detail screen is displayed with all elements
            onNodeWithTag("back_button").assertExists().assertIsDisplayed()
            onNodeWithTag("artwork_image").assertExists().assertIsDisplayed()
            onNodeWithTag("artwork_description").assertExists().assertIsDisplayed()
        }
    }

    @Test
    fun testArtworkDetailBackNavigation() {
        with(composeActivityTestRule) {
            waitForIdle()
            waitUntilAtLeastOneExists(hasTestTag("artwork_list"))
            // set up to get to the detail screen
            onAllNodesWithTag("artwork_card")[0].assertExists().performClick()

            // assert the back button is there and click it to check for the artwork list
            onNodeWithTag("back_button").assertExists().assertIsDisplayed().performClick()
            onNodeWithTag("artwork_list").assertExists()
        }
    }
}