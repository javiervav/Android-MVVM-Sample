package com.android.domain.usecases

import com.android.domain.Result
import com.android.domain.models.SearchItem
import com.android.domain.repositories.AuthRepositoryContract
import com.android.domain.repositories.MusicRepositoryContract
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetAlbumInfoUseCaseTest {

    private lateinit var getAlbumInfoUseCase: GetAlbumInfoUseCase
    private val authRepository: AuthRepositoryContract = Mockito.mock(AuthRepositoryContract::class.java)
    private val musicRepository: MusicRepositoryContract = Mockito.mock(MusicRepositoryContract::class.java)

    @Before
    fun setup() {
        getAlbumInfoUseCase = GetAlbumInfoUseCase(authRepository, musicRepository)
    }

    @Test
    fun `should call get album list with correct access token and text`() {
        val text = "mockText"
        val accessToken = "mockAccessToken"
        whenever(authRepository.getAccessToken()).thenReturn(Result.Success(accessToken))

        getAlbumInfoUseCase.execute(text)

        verify(authRepository, times(1)).getAccessToken()
        verify(musicRepository, times(1)).getAlbumList(accessToken, text)
    }

    @Test
    fun `should return success with correct data when requests succeed`() {
        val text = "mockText"
        val accessToken = "mockAccessToken"
        whenever(authRepository.getAccessToken()).thenReturn(Result.Success(accessToken))
        whenever(musicRepository.getAlbumList(accessToken, text)).thenReturn(Result.Success(givenAlbumList()))

        val result = getAlbumInfoUseCase.execute(text)

        assertEquals(result, Result.Success(givenAlbumList()))
    }

    @Test
    fun `should not search albums and return failure when get token request fails`() {
        val text = "mockText"
        whenever(authRepository.getAccessToken()).thenReturn(Result.Failure)

        val result = getAlbumInfoUseCase.execute(text)

        assertEquals(result, Result.Failure)
    }

    private fun givenAlbumList() = listOf(
        SearchItem.Album("mockId1", "mockName1", "mockArtist1", null, "mockReleaseDate", 0),
        SearchItem.Album("mockId2", "mockName2", "mockArtist2", null, "mockReleaseDate", 0)
    )
}
