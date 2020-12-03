package com.android.organizer.presentation

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<T : BaseContract.Presenter> : BaseContract.View, AppCompatActivity() {

    @Inject
    lateinit var presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies()

        presenter.attachView(this, lifecycleScope)
    }

    private fun injectDependencies() {
        AndroidInjection.inject(getActivityContext())
    }

    abstract fun getActivityContext(): Activity
}
