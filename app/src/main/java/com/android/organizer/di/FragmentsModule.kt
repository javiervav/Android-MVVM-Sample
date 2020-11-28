package com.android.organizer.di

import com.android.organizer.presentation.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun provideSearchFragment(): SearchFragment
}
