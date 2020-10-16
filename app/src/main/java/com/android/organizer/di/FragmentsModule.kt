package com.android.organizer.di

import com.android.organizer.presentation.search.SearchFragment
import com.android.organizer.presentation.search.dialog.SearchDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun provideSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun provideSearchDialogFragment(): SearchDialogFragment
}
