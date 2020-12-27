package com.android.mvvmsample.presentation.main

import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import com.android.mvvmsample.R
import com.android.mvvmsample.presentation.search.SearchFragmentDirections
import com.android.mvvmsample.presentation.search.SearchType
import com.google.android.material.bottomnavigation.BottomNavigationView

@BindingAdapter("bottomSheetAnimatedVisibility")
fun View.setAnimatedVisibility(showOptions: Boolean?) {
    val destPositionY = if (showOptions == true) -resources.getDimension(R.dimen.search_bottom_sheet_height) else 0f
    animate()
        .translationY(destPositionY)
        .setInterpolator(DecelerateInterpolator())
        .setDuration(resources.getInteger(android.R.integer.config_mediumAnimTime).toLong())
        .start()
}

@BindingAdapter("containerContent")
fun FragmentContainerView.setContent(searchType: SearchType?) {
    searchType?.let {
        val action = SearchFragmentDirections.actionGlobalSearchFragment(searchTypeArg = searchType)
        findNavController().navigate(action)
    }
}

@BindingAdapter("searchOptionSelected")
fun BottomNavigationView.setSearchOptionSelected(isSelected: Boolean? = false) {
    if (isSelected == true) {
        menu.findItem(R.id.searchBottomSheetDialog).isChecked = true
    }
}
