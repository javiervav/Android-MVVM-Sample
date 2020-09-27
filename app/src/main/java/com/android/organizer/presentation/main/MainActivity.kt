package com.android.organizer.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.organizer.R
import com.android.organizer.presentation.BaseActivity
import com.android.organizer.utils.extensions.toPx
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
                Log.d("SlideOffset = ", slideOffset.toString())
                val searchBottomSheetLayoutParams = searchBottomSheetLayout.layoutParams as LinearLayout.LayoutParams
                searchBottomSheetLayoutParams.bottomMargin = (slideOffset * 149.toFloat().toPx).toInt()
                searchBottomSheetLayout.layoutParams = searchBottomSheetLayoutParams
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {}
        })
    }
}
