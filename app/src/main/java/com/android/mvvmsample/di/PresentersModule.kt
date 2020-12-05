package com.android.mvvmsample.di

import com.android.domain.usecases.GetArtistInfoUseCase
import com.android.mvvmsample.presentation.main.MainContract
import com.android.mvvmsample.presentation.main.MainPresenter
import com.android.mvvmsample.presentation.search.SearchContract
import com.android.mvvmsample.presentation.search.SearchPresenter
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
    fun provideSearchPresenter(getArtistInfoUseCase: GetArtistInfoUseCase): SearchContract.Presenter =
        SearchPresenter(getArtistInfoUseCase)
}
