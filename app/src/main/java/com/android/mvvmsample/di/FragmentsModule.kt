package com.android.mvvmsample.di

import com.android.mvvmsample.presentation.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun provideSearchFragment(): SearchFragment
}
