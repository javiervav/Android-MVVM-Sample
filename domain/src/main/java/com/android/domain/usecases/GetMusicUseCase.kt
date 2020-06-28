package com.android.domain.usecases

import com.android.domain.models.MusicInfo
import com.android.domain.repositories.AuthRepositoryContract
import com.android.domain.repositories.MusicRepositoryContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetMusicUseCase(
    private val authRepository: AuthRepositoryContract,
    private val musicRepository: MusicRepositoryContract
) {
    fun execute(text: String, resultado: (MusicInfo) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = run(text)
            withContext(Dispatchers.Main) {
                resultado.invoke(result)
            }
        }
    }

    private fun run(text: String): MusicInfo {
        val accessToken = authRepository.getAccessToken() // TODO: Save accessToken in database and when api fails, call it again (Interceptor)
        val albumList = musicRepository.getAlbumList(accessToken!!, text)
        val artistList = musicRepository.getArtistList(accessToken, text)
        return MusicInfo(artistList, albumList)
    }
}
