package com.android.data

import com.android.data.mock.MockResponseFileReader
import com.android.data.remote.AuthApi
import com.android.data.repository.AuthRepositoryImpl
import com.android.domain.Result
import com.android.domain.repositories.AuthRepositoryContract
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthRepositoryImplTest {

    private val mockWebServer: MockWebServer = MockWebServer()
    private lateinit var authApi: AuthApi

    private lateinit var authRepository: AuthRepositoryContract

    @Before
    fun setup() {
        mockWebServer.start(8000)
        authApi = givenMockAuthApi()
        authRepository = AuthRepositoryImpl(authApi)
    }

    @After
    fun after() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should return access token when get access token is requested`() {
        mockWebServer.enqueue(MockResponse().setBody(MockResponseFileReader("getAccessToken.json").content))

        val result = authRepository.getAccessToken()

        assertTrue(result is Result.Success)
        assertEquals((result as Result.Success).value, "mockAccessToken")
    }

    @Test
    fun `should return failure when get access token request is not success`() {
        mockWebServer.enqueue(MockResponse().setResponseCode(400))

        val result = authRepository.getAccessToken()

        assertTrue(result is Result.Failure)
    }

    private fun givenMockAuthApi(): AuthApi = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApi::class.java)
}
