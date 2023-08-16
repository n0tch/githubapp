package com.example.githubapp.features.users

import androidx.activity.compose.setContent
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.githubapp.AppScreen
import com.example.githubapp.MainActivity
import com.example.githubapp.R
import com.example.githubapp.features.navigation.AppNavHost
import com.example.githubapp.components.SEARCH_BUTTON_TAG
import com.example.githubapp.components.TEXT_FIELD_TAG
import com.example.githubapp.features.users.ui.USER_LIST_TAG
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class UsersScreenKtTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun searchForUserAndAssertThatItListASpecificRepository() {
        with(composeRule) {
            val username = "n0tch"
            val searchTextContent = activity.getString(R.string.users_top_app_search_text)

            activity.apply {
                setContent {
                    AppScreen()
                }
            }

            onNodeWithContentDescription(searchTextContent).performClick()
            onNodeWithTag(TEXT_FIELD_TAG).performTextInput(username)
            onNodeWithTag(SEARCH_BUTTON_TAG).performClick()
            waitUntilExactlyOneExists(hasTestTag(username), timeoutMillis = 5000L)
            onNodeWithTag(USER_LIST_TAG).performScrollToNode(hasTestTag(username)).assertExists()
            onNodeWithTag(username).performClick()
            waitUntilExactlyOneExists(hasText(username), timeoutMillis = 5000L)
            waitUntilExactlyOneExists(hasTestTag("n0tch/agenda"), timeoutMillis = 5000L)
        }
    }
}
