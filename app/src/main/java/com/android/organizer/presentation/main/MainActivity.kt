package com.android.organizer.presentation.main

import android.os.Bundle
import android.widget.Toast
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

    override fun showHomeScreen() {
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
    }

    override fun showBookListScreen() {
        Toast.makeText(this, "Book", Toast.LENGTH_SHORT).show()
    }

    override fun showSearchScreen() {
        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
    }

    override fun showMusicScreen() {
        Toast.makeText(this, "Music", Toast.LENGTH_SHORT).show()
    }

    override fun showProfileScreen() {
        Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {
        mainBottomNavigationView.setOnNavigationItemSelectedListener { item ->
            presenter.onBottomNavigationClicked(item)
        }
    }
}
