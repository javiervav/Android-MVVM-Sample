package com.android.domain.usecases

import com.android.domain.Result
import com.android.domain.models.SearchItem
import com.android.domain.repositories.AuthRepositoryContract
import com.android.domain.repositories.MusicRepositoryContract

class GetArtistInfoUseCase(
    private val authRepository: AuthRepositoryContract,
    private val musicRepository: MusicRepositoryContract
) {

    fun execute(text: String, offset: Int = 0): Result<List<SearchItem.Artist>> {
        // TODO: Save accessToken in database and when api fails, call it again (Interceptor)
        val accessToken = authRepository.getAccessToken()
        return if (accessToken is Result.Success<String>) {
            musicRepository.getArtistList(accessToken.value, text, offset)
        } else {
            Result.Failure
        }
    }
}
