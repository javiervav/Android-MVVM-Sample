package com.android.organizer.presentation.main

import android.view.MenuItem
import com.android.organizer.R

class MainPresenter : MainContract.Presenter {

    lateinit var view: MainContract.View // TODO: Move to a BasePresenter

    override fun attachView(view: MainContract.View) { // TODO: Move to a BasePresenter
        this.view = view
    }

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
