package com.android.mvvmsample.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.mvvmsample.R
import com.android.mvvmsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initViews()
    }

    private fun initDataBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = ViewModelProvider(this@MainActivity).get()
            lifecycleOwner = this@MainActivity
        }
        setContentView(binding.root)
    }

    private fun initViews() {
        setupBottomNavigationBarNavigation()
    }

    private fun setupBottomNavigationBarNavigation() {
        supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView)
            ?.let { navHostFragment ->
                NavigationUI.setupWithNavController(
                    binding.mainBottomNavigationView,
                    navHostFragment.findNavController()
                )
            }
    }
}
