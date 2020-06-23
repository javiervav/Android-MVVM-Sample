package com.android.organizer.di

import com.android.organizer.presentation.main.MainActivity
import com.android.organizer.presentation.search.music.SearchMusicFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}
