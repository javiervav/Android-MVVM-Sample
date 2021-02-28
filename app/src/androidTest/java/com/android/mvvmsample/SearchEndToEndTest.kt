package com.android.mvvmsample

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.android.mvvmsample.presentation.main.MainActivity
import com.android.mvvmsample.utils.DisableAnimationsRule
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListNotEmpty
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@ExperimentalCoroutinesApi
class SearchEndToEndTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val disableAnimationsRule = DisableAnimationsRule()

    @Test
    fun performArtistSearch() {
        clickOn(R.id.menuSearchButton)
        clickOn(R.id.bottomSheetFirstOption)
        writeTo(R.id.searchEt, "Iron Maiden")

        Thread.sleep(3000) // TODO: Avoid sleep. Check Espresso Idling Resources.

        assertListNotEmpty(R.id.searchRv)
    }

    @Test
    fun performAlbumSearch() {
        clickOn(R.id.menuSearchButton)
        clickOn(R.id.bottomSheetSecondOption)
        writeTo(R.id.searchEt, "Piece of Mind")

        Thread.sleep(3000)

        assertListNotEmpty(R.id.searchRv)
    }
}
