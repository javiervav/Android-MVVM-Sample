package com.android.mvvmsample

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.android.mvvmsample.presentation.main.MainActivity
import com.android.mvvmsample.utils.DisableAnimationsRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import org.junit.Rule
import org.junit.Test

class BottomNavigationViewTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val disableAnimationsRule = DisableAnimationsRule()

    @Test
    fun shouldShowHomeScreenWhenHomeIsClickedInBottomNavigationView() {
        assertDisplayed(R.id.homeTitle, "HOME")

        clickOn(R.id.booksFragment)
        clickOn(R.id.homeFragment)

        assertDisplayed(R.id.homeTitle, "HOME")
    }

    @Test
    fun shouldShowBooksScreenWhenBooksIsClickedInBottomNavigationView() {
        clickOn(R.id.booksFragment)

        assertDisplayed(R.id.booksTitle, "BOOKS")
    }

    @Test
    fun shouldShowSearchOptionsWhenSearchIsClickedInBottomNavigationView() {
        clickOn(R.id.menuSearchButton)

        assertDisplayed(R.id.searchBottomSheetLayout)
        assertDisplayed(R.id.bottomSheetFirstOption, "Artist")
        assertDisplayed(R.id.bottomSheetSecondOption, "Album")
    }

    @Test
    fun shouldShowMusicScreenWhenMusicIsClickedInBottomNavigationView() {
        clickOn(R.id.musicFragment)

        assertDisplayed(R.id.musicTitle, "MUSIC")
    }

    @Test
    fun shouldShowProfileScreenWhenProfileIsClickedInBottomNavigationView() {
        clickOn(R.id.profileFragment)

        assertDisplayed(R.id.profileTitle, "PROFILE")
    }
}
