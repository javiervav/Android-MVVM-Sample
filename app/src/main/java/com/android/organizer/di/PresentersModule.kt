package com.android.organizer.di

import com.android.domain.usecases.GetMusicUseCase
import com.android.organizer.presentation.main.MainContract
import com.android.organizer.presentation.main.MainPresenter
import com.android.organizer.presentation.search.music.SearchMusicContract
import com.android.organizer.presentation.search.music.SearchMusicPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentersModule {

    @Singleton
    @Provides
    fun provideMainPresenter(): MainContract.Presenter = MainPresenter()

    @Singleton
    @Provides
    fun provideSearchMusicPresenter(getMusicUseCase: GetMusicUseCase): SearchMusicContract.Presenter = SearchMusicPresenter(getMusicUseCase)
}
