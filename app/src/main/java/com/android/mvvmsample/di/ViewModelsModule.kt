package com.android.mvvmsample.di

import androidx.lifecycle.ViewModel
import com.android.mvvmsample.presentation.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}
