package com.android.data.di

import com.android.data.remote.AuthApi
import com.android.data.remote.MusicApi
import com.android.data.repository.AuthRepository
import com.android.data.repository.MusicRepository
import com.android.domain.repositories.AuthRepositoryContract
import com.android.domain.repositories.MusicRepositoryContract
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class DataModule {

    @Singleton
    @Provides
    fun providesAuthRepository(): AuthRepositoryContract = AuthRepository(getRetrofitBuilderAuth())

    @Singleton
    @Provides
    fun providesMusicRepository(): MusicRepositoryContract = MusicRepository(getRetrofitBuilderMusic())

    private fun getRetrofitBuilderAuth() = Retrofit.Builder()
        .baseUrl(AuthApi.getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApi::class.java)

    private fun getRetrofitBuilderMusic() = Retrofit.Builder()
        .baseUrl(MusicApi.getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MusicApi::class.java)
}
