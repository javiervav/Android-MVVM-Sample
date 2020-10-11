package com.android.organizer.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.organizer.R
import com.android.organizer.presentation.BaseActivity
import com.android.organizer.presentation.search.SearchFragmentDirections
import com.android.organizer.presentation.search.SearchType
import com.android.organizer.utils.extensions.toPx
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_bottom_sheet.*

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    override fun getActivityContext() = this

    private fun initViews() {
        setupBottomNavigationBarNavigation()
        setupSearchBottomSheet()
        mainBottomNavigationCenterButton.setOnClickListener { presenter.onSearchButtonClick() }
        bottomSheetFirstOption.setOnClickListener { presenter.onSearchArtistClick() }
        bottomSheetSecondOption.setOnClickListener { presenter.onSearchAlbumClick() }
    }

    private fun setupBottomNavigationBarNavigation() {
        supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView)?.let { navHostFragment ->
            NavigationUI.setupWithNavController(mainBottomNavigationView, navHostFragment.findNavController())
        }
    }

    private fun setupSearchBottomSheet() {
        val bottomSheetBehavior = BottomSheetBehavior.from(mainBottomNavigationCenterButton);
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                presenter.onBottomSheetSlide(slideOffset)
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {}
        })
    }

    override fun isBottomSheetExpanded() = BottomSheetBehavior.from(mainBottomNavigationCenterButton).state == STATE_EXPANDED

    override fun expandBottomSheet() {
        BottomSheetBehavior.from(mainBottomNavigationCenterButton).state = STATE_EXPANDED
    }

    override fun collapseBottomSheet() {
        BottomSheetBehavior.from(mainBottomNavigationCenterButton).state = STATE_COLLAPSED
    }

    override fun moveBottomSheetVertically(slideOffset: Float) {
        val searchBottomSheetLayoutParams = searchBottomSheetLayout.layoutParams as LinearLayout.LayoutParams
        searchBottomSheetLayoutParams.bottomMargin = (slideOffset * 174.toFloat().toPx).toInt()
        searchBottomSheetLayout.layoutParams = searchBottomSheetLayoutParams
    }

    override fun navigateToSearch(searchType: SearchType) {
        val action = SearchFragmentDirections.actionGlobalSearchFragment(searchType = searchType)
        findNavController(R.id.mainFragmentContainerView).navigate(action)
    }
}
