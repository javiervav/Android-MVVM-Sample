package com.android.organizer.presentation.main

import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.organizer.R
import com.android.organizer.presentation.BaseActivity
import com.android.organizer.presentation.search.SearchFragmentDirections
import com.android.organizer.presentation.search.SearchType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    override fun getActivityContext() = this

    private fun initViews() {
        setupBottomNavigationBarNavigation()
        menuSearchButton.setOnClickListener { presenter.onMenuSearchButtonClick() }
        bottomSheetFirstOption.setOnClickListener { presenter.onSearchArtistClick() }
        bottomSheetSecondOption.setOnClickListener { presenter.onSearchAlbumClick() }
    }

    private fun setupBottomNavigationBarNavigation() {
        supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView)?.let { navHostFragment ->
            NavigationUI.setupWithNavController(mainBottomNavigationView, navHostFragment.findNavController())
        }
    }

    override fun isBottomSheetHidden() = menuSearchButton.translationY == 0f

    override fun toggleSearchOptionsLayoutVisibility(showOptions: Boolean) {
        startSearchOptionsAnimation(menuSearchButton, showOptions)
        startSearchOptionsAnimation(searchBottomSheetLayout, showOptions)
    }

    override fun selectNavigationBarSearchOption() {
        mainBottomNavigationView.menu.findItem(R.id.searchBottomSheetDialog).isChecked = true
    }

    override fun navigateToSearch(searchType: SearchType) {
        val action = SearchFragmentDirections.actionGlobalSearchFragment(searchType = searchType)
        findNavController(R.id.mainFragmentContainerView).navigate(action)
    }

    private fun startSearchOptionsAnimation(view: View, showOptions: Boolean) {
        val destinationPositionY = if (showOptions)  -searchBottomSheetLayout.height.toFloat() else 0f
        view.animate()
            .translationY(destinationPositionY)
            .setInterpolator(DecelerateInterpolator())
            .setDuration(resources.getInteger(android.R.integer.config_mediumAnimTime).toLong())
            .start()
    }
}
