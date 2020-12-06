package com.android.mvvmsample.di

import com.android.mvvmsample.presentation.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@Module
abstract class FragmentsModule {

    @ExperimentalCoroutinesApi
    @FlowPreview
    @ContributesAndroidInjector
    abstract fun provideSearchFragment(): SearchFragment
}
