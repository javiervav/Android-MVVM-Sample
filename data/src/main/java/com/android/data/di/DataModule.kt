package com.android.data.di

import com.android.data.remote.auth.AuthApi
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
    fun providesAuthRepository(): AuthRepositoryContract = AuthRepository(getRetrofitBuilder())

    @Singleton
    @Provides
    fun providesMusicRepository(): MusicRepositoryContract = MusicRepository()

    private fun getRetrofitBuilder() = Retrofit.Builder()
        .baseUrl(AuthApi.getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApi::class.java)
}
