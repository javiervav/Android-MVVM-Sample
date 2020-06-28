package com.android.domain.di

import com.android.domain.repositories.AuthRepositoryContract
import com.android.domain.repositories.MusicRepositoryContract
import com.android.domain.usecases.GetMusicUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun providesGetMusicUseCase(authRepository: AuthRepositoryContract, musicRepository: MusicRepositoryContract): GetMusicUseCase =
        GetMusicUseCase(authRepository, musicRepository)
}
