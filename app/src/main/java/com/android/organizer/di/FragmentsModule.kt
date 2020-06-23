package com.android.organizer.di

import com.android.organizer.presentation.search.music.SearchMusicFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun provideSearchMusicFragment(): SearchMusicFragment
}
