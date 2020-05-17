package com.android.organizer.presentation.main

import android.view.MenuItem
import com.android.organizer.presentation.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun showHomeScreen()
        fun showBookListScreen()
        fun showSearchScreen()
        fun showMusicScreen()
        fun showProfileScreen()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onBottomNavigationClicked(menuItem: MenuItem): Boolean
    }
}
