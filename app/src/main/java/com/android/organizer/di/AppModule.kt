package com.android.organizer.di

import android.content.Context
import com.android.organizer.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(mainApplication: MainApplication): Context = mainApplication
}
