package com.android.mvvmsample.di

import android.content.Context
import com.android.mvvmsample.shared.networkImageLoader.GlideImageLoader
import com.android.mvvmsample.shared.networkImageLoader.NetworkImageLoader
import dagger.Module
import dagger.Provides

@Module
class SharedModule {

    @Provides
    fun provideNetworkImageLoader(context: Context): NetworkImageLoader {
        return GlideImageLoader(context)
    }
}
