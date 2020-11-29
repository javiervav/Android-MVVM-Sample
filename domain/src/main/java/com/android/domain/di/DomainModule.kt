package com.android.domain.di

import com.android.domain.repositories.AuthRepositoryContract
import com.android.domain.repositories.MusicRepositoryContract
import com.android.domain.usecases.GetArtistInfoUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun providesGetArtistInfoUseCase(authRepository: AuthRepositoryContract, musicRepository: MusicRepositoryContract): GetArtistInfoUseCase =
        GetArtistInfoUseCase(authRepository, musicRepository)
}
