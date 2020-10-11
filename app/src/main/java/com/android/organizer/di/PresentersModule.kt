package com.android.organizer.di

import com.android.organizer.presentation.main.MainContract
import com.android.organizer.presentation.main.MainPresenter
import com.android.organizer.presentation.search.SearchContract
import com.android.organizer.presentation.search.SearchPresenter
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
    fun provideSearchPresenter(): SearchContract.Presenter = SearchPresenter()

}
