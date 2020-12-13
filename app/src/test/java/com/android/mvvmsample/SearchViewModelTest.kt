package com.android.mvvmsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.domain.Result
import com.android.domain.models.Artist
import com.android.domain.usecases.GetArtistInfoUseCase
import com.android.mvvmsample.presentation.search.SearchViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    private val getArtistInfoUseCase: GetArtistInfoUseCase = Mockito.mock(GetArtistInfoUseCase::class.java)
    private val artistList: List<Artist> = listOf(Mockito.mock(Artist::class.java), Mockito.mock(Artist::class.java))
    private lateinit var searchViewModel: SearchViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        searchViewModel = SearchViewModel(testDispatcher, getArtistInfoUseCase)

        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun `should not load results if text is shorter than three digits`() {
        searchViewModel.searchInfo("AA")

        assertNull(searchViewModel.toggleLoaderVisibility.value)
        assertNull(searchViewModel.artistList.value)
        assertNull(searchViewModel.errorLayoutVisibility.value)
    }

    @Test
    fun `should load results if text is longer than three digits`() {
        val searchText = "Queen"
        val toggleLoaderVisibilityObserver = mock<Observer<Boolean>>()
        searchViewModel.toggleLoaderVisibility.observeForever(toggleLoaderVisibilityObserver)
        val artistListObserver = mock<Observer<List<Artist>>>()
        searchViewModel.artistList.observeForever(artistListObserver)
        whenever(getArtistInfoUseCase.execute(searchText)).thenReturn(Result.Success(artistList))

        searchViewModel.searchInfo(searchText)

        verify(toggleLoaderVisibilityObserver).onChanged(true)
        verify(toggleLoaderVisibilityObserver).onChanged(false)
        verify(artistListObserver).onChanged(artistList)
        assertNull(searchViewModel.errorLayoutVisibility.value)
    }

    @Test
    fun `should load error if get artist request fails`() {
        val searchText = "Queen"
        val toggleLoaderVisibilityObserver = mock<Observer<Boolean>>()
        searchViewModel.toggleLoaderVisibility.observeForever(toggleLoaderVisibilityObserver)
        val toggleErrorLayoutVisibilityObserver = mock<Observer<Boolean>>()
        searchViewModel.errorLayoutVisibility.observeForever(toggleErrorLayoutVisibilityObserver)
        whenever(getArtistInfoUseCase.execute(searchText)).thenReturn(Result.Failure)

        searchViewModel.searchInfo(searchText)

        verify(toggleLoaderVisibilityObserver).onChanged(true)
        verify(toggleLoaderVisibilityObserver).onChanged(false)
        assertNull(searchViewModel.artistList.value)
        verify(toggleErrorLayoutVisibilityObserver).onChanged(true)
    }
}