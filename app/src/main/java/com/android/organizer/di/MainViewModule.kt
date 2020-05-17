package com.android.organizer.di

import com.android.organizer.presentation.main.MainActivity
import com.android.organizer.presentation.main.MainContract
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class MainViewModule {

    @Singleton
    @Binds
    abstract fun provideMainView(mainActivity: MainActivity): MainContract.View
}
