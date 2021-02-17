package com.android.domain

import com.android.domain.models.SearchItem
import com.android.domain.repositories.AuthRepositoryContract
import com.android.domain.repositories.MusicRepositoryContract
import com.android.domain.usecases.GetArtistInfoUseCase
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetArtistInfoUseCaseTest {

    private lateinit var getArtistInfoUseCase: GetArtistInfoUseCase
    private val authRepository: AuthRepositoryContract = Mockito.mock(AuthRepositoryContract::class.java)
    private val musicRepository: MusicRepositoryContract = Mockito.mock(MusicRepositoryContract::class.java)

    @Before
    fun setup() {
        getArtistInfoUseCase = GetArtistInfoUseCase(authRepository, musicRepository)
    }

    @Test
    fun `should call get artist list with correct access token and text`() {
        val text = "mockText"
        val accessToken = "mockAccessToken"
        whenever(authRepository.getAccessToken()).thenReturn(Result.Success(accessToken))

        getArtistInfoUseCase.execute(text)

        verify(authRepository, times(1)).getAccessToken()
        verify(musicRepository, times(1)).getArtistList(accessToken, text)
    }

    @Test
    fun `should return success with correct data when requests succeed`() {
        val text = "mockText"
        val accessToken = "mockAccessToken"
        whenever(authRepository.getAccessToken()).thenReturn(Result.Success(accessToken))
        whenever(musicRepository.getArtistList(accessToken, text)).thenReturn(Result.Success(givenArtistList()))

        val result = getArtistInfoUseCase.execute(text)

        assertEquals(result, Result.Success(givenArtistList()))
    }

    @Test
    fun `should not search artists and return failure when get token request fails`() {
        val text = "mockText"
        whenever(authRepository.getAccessToken()).thenReturn(Result.Failure)

        val result = getArtistInfoUseCase.execute(text)

        assertEquals(result, Result.Failure)
    }

    private fun givenArtistList() = listOf(
        SearchItem.Artist("mockId1", "mockName1", null, null, null, 0, "mockSpotifyUrl1"),
        SearchItem.Artist("mockId2", "mockName2", null, null, null, 0, "mockSpotifyUrl2")
    )
}
