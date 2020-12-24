package com.android.mvvmsample.presentation.main

import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.mvvmsample.R
import com.android.mvvmsample.presentation.search.SearchFragmentDirections
import com.android.mvvmsample.presentation.search.SearchType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get()

        initObservables()
        initViews()
    }

    private fun initObservables() {
        mainViewModel.searchOptionsLayoutVisibility.observe(this, Observer { visible ->
            startSearchOptionsAnimation(menuSearchButton, visible)
            startSearchOptionsAnimation(searchBottomSheetLayout, visible)
        })

        mainViewModel.navigationBarSearchOptionSelected.observe(this, Observer { selected ->
            mainBottomNavigationView.menu.findItem(R.id.searchBottomSheetDialog).isChecked = selected
        })
    }

    private fun initViews() {
        setupBottomNavigationBarNavigation()
        menuSearchButton.setOnClickListener { mainViewModel.onMenuSearchButtonClick() }
        bottomSheetFirstOption.setOnClickListener {
            mainViewModel.onSearchArtistClick()
            navigateToSearch(SearchType.ARTIST)
        }
        bottomSheetSecondOption.setOnClickListener {
            mainViewModel.onSearchAlbumClick()
            navigateToSearch(SearchType.ALBUM)
        }
    }

    private fun setupBottomNavigationBarNavigation() {
        supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView)
            ?.let { navHostFragment ->
                NavigationUI.setupWithNavController(
                    mainBottomNavigationView,
                    navHostFragment.findNavController()
                )
            }
    }

    private fun navigateToSearch(searchType: SearchType) {
        val action = SearchFragmentDirections.actionGlobalSearchFragment(searchTypeArg = searchType)
        findNavController(R.id.mainFragmentContainerView).navigate(action)
    }

    private fun startSearchOptionsAnimation(view: View, showOptions: Boolean) {
        val destinationPositionY =
            if (showOptions) -searchBottomSheetLayout.height.toFloat() else 0f
        view.animate()
            .translationY(destinationPositionY)
            .setInterpolator(DecelerateInterpolator())
            .setDuration(resources.getInteger(android.R.integer.config_mediumAnimTime).toLong())
            .start()
    }
}
