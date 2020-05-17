package com.android.organizer.presentation.main

import android.view.MenuItem
import com.android.organizer.R
import com.android.organizer.presentation.BasePresenter

class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun onBottomNavigationClicked(menuItem: MenuItem): Boolean =
        when (menuItem.itemId) {
            R.id.menuPageOne -> {
                view.showHomeScreen()
                true
            }
            R.id.menuPageTwo -> {
                view.showBookListScreen()
                true
            }
            R.id.menuPageThree -> {
                view.showSearchScreen()
                true
            }
            R.id.menuPageFour -> {
                view.showMusicScreen()
                true
            }
            R.id.menuPageFive -> {
                view.showProfileScreen()
                true
            }
            else -> false
        }
}
