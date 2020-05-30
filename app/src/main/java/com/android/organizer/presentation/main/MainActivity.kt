package com.android.organizer.presentation.main

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.organizer.R
import com.android.organizer.presentation.BaseActivity
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
    }

    private fun setupBottomNavigationBarNavigation() {
        supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView)?.let { navHostFragment ->
            NavigationUI.setupWithNavController(mainBottomNavigationView, navHostFragment.findNavController())
        }
    }
}
