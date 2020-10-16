package com.android.domain.usecases

import com.android.domain.models.Artist
import com.android.domain.repositories.AuthRepositoryContract
import com.android.domain.repositories.MusicRepositoryContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetArtistInfoUseCase(
    private val authRepository: AuthRepositoryContract,
    private val musicRepository: MusicRepositoryContract
) {

    fun execute(text: String, callback: (List<Artist>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = run(text)
            withContext(Dispatchers.Main) {
                callback.invoke(result)
            }
        }
    }

    private fun run(text: String): List<Artist> {
        val accessToken = authRepository.getAccessToken() // TODO: Save accessToken in database and when api fails, call it again (Interceptor)
        return musicRepository.getArtistList(accessToken!!, text) // TODO: Remove exclamation marks
    }
}
