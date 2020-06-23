package com.android.organizer.di

import com.android.organizer.presentation.main.MainActivity
import com.android.organizer.presentation.search.music.SearchMusicFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun provideSearchMusicFragment(): SearchMusicFragment
}
