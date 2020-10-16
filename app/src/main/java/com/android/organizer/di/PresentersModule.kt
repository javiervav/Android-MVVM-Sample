package com.android.organizer.di

import com.android.domain.usecases.GetArtistInfoUseCase
import com.android.organizer.presentation.main.MainContract
import com.android.organizer.presentation.main.MainPresenter
import com.android.organizer.presentation.search.SearchContract
import com.android.organizer.presentation.search.SearchPresenter
import com.android.organizer.presentation.search.dialog.SearchDialogContract
import com.android.organizer.presentation.search.dialog.SearchDialogPresenter
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
    fun provideSearchPresenter(getArtistInfoUseCase: GetArtistInfoUseCase): SearchContract.Presenter = SearchPresenter(getArtistInfoUseCase)

    @Singleton
    @Provides
    fun provideSearchDialogPresenter(): SearchDialogContract.Presenter = SearchDialogPresenter()
}
