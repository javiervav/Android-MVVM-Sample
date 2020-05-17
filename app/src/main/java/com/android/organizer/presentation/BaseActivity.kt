package com.android.organizer.presentation

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies()
    }

    private fun injectDependencies() {
        AndroidInjection.inject(getActivityContext())
    }

    abstract fun getActivityContext(): Activity
}
