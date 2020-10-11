package com.android.organizer.di

import com.android.organizer.presentation.main.MainContract
import com.android.organizer.presentation.main.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentersModule {

    @Singleton
    @Provides
    fun provideMainPresenter(): MainContract.Presenter = MainPresenter()

}
