package com.android.organizer.di

import com.android.organizer.presentation.main.MainContract
import com.android.organizer.presentation.main.MainPresenter
import com.android.organizer.presentation.search.music.SearchMusicContract
import com.android.organizer.presentation.search.music.SearchMusicPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainScreenModule {

    @Singleton
    @Provides
    fun provideMainPresenter(): MainContract.Presenter = MainPresenter()

    @Singleton
    @Provides
    fun provideSearchMusicPresenter(): SearchMusicContract.Presenter = SearchMusicPresenter()
}
