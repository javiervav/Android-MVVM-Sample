package com.android.mvvmsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.domain.Result
import com.android.domain.models.SearchItem
import com.android.domain.usecases.GetAlbumInfoUseCase
import com.android.domain.usecases.GetArtistInfoUseCase
import com.android.mvvmsample.presentation.search.SearchType
import com.android.mvvmsample.presentation.search.SearchViewModel
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    private val getArtistInfoUseCase: GetArtistInfoUseCase = Mockito.mock(GetArtistInfoUseCase::class.java)
    private val getAlbumInfoUseCase: GetAlbumInfoUseCase = Mockito.mock(GetAlbumInfoUseCase::class.java)
    private val artistList: List<SearchItem.Artist> = listOf(
        Mockito.mock(SearchItem.Artist::class.java),
        Mockito.mock(SearchItem.Artist::class.java)
    )
    private val albumList: List<SearchItem.Album> = listOf(
        Mockito.mock(SearchItem.Album::class.java),
        Mockito.mock(SearchItem.Album::class.java),
        Mockito.mock(SearchItem.Album::class.java)
    )
    private lateinit var searchViewModel: SearchViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        searchViewModel = SearchViewModel(testDispatcher, getArtistInfoUseCase, getAlbumInfoUseCase)

        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun `should not load artists if text is shorter than three digits`() {
        searchViewModel.searchInfo("Qu", SearchType.ARTIST)

        assertNull(searchViewModel.isLoading.value)
        assertNull(searchViewModel.searchItemList.value)
        assertNull(searchViewModel.errorLayoutVisibility.value)
    }

    @Test
    fun `should not load albums if text is shorter than three digits`() {
        searchViewModel.searchInfo("A", SearchType.ALBUM)

        assertNull(searchViewModel.isLoading.value)
        assertNull(searchViewModel.searchItemList.value)
        assertNull(searchViewModel.errorLayoutVisibility.value)
    }

    @Test
    fun `should load artists if text is longer than three digits`() {
        val searchText = "Queen"
        whenever(getArtistInfoUseCase.execute(searchText)).thenReturn(Result.Success(artistList))

        searchViewModel.searchInfo(searchText, SearchType.ARTIST)

        assertEquals(searchViewModel.searchItemList.value, artistList)
        assertEquals(searchViewModel.errorLayoutVisibility.value, false)
    }

    @Test
    fun `should load albums if text is longer than three digits`() {
        val searchText = "A night at the opera"
        whenever(getAlbumInfoUseCase.execute(searchText)).thenReturn(Result.Success(albumList))

        searchViewModel.searchInfo(searchText, SearchType.ALBUM)

        assertEquals(searchViewModel.searchItemList.value, albumList)
        assertEquals(searchViewModel.errorLayoutVisibility.value, false)
    }

    @Test
    fun `should load error if get artists request fails`() {
        val searchText = "Queen"
        whenever(getArtistInfoUseCase.execute(searchText)).thenReturn(Result.Failure)

        searchViewModel.searchInfo(searchText, SearchType.ARTIST)

        assertNull(searchViewModel.searchItemList.value)
        assertEquals(searchViewModel.errorLayoutVisibility.value, true)
    }

    @Test
    fun `should load error if get albums request fails`() {
        val searchText = "A night at the opera"
        whenever(getAlbumInfoUseCase.execute(searchText)).thenReturn(Result.Failure)

        searchViewModel.searchInfo(searchText, SearchType.ALBUM)

        assertNull(searchViewModel.searchItemList.value)
        assertEquals(searchViewModel.errorLayoutVisibility.value, true)
    }
}
