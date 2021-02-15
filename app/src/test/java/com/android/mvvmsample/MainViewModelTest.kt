package com.android.mvvmsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.mvvmsample.presentation.main.MainViewModel
import com.android.mvvmsample.presentation.search.SearchType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    //    Force all LiveData in the tests to be executed in the thread of the test instead of Android main thread.
    //    Needed to avoid following error: Method getMainLooper in android.os.Looper not mocked
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        mainViewModel = MainViewModel()
    }

    @Test
    fun `should show search options layout when clicking search button and is hidden`() {
        assertNull(mainViewModel.searchOptionsLayoutVisibility.value)

        mainViewModel.onMenuSearchButtonClick()

        assertEquals(mainViewModel.searchOptionsLayoutVisibility.value, true)
    }

    @Test
    fun `should hide search options layout when clicking search button and is shown`() {
        mainViewModel.onMenuSearchButtonClick()
        assertEquals(mainViewModel.searchOptionsLayoutVisibility.value, true)

        mainViewModel.onMenuSearchButtonClick()

        assertEquals(mainViewModel.searchOptionsLayoutVisibility.value, false)
    }

    @Test
    fun `should hide search options layout when clicking search artist option`() {
        mainViewModel.onSearchArtistClick()

        assertEquals(mainViewModel.searchOptionsLayoutVisibility.value, false)
    }

    @Test
    fun `should select search in bottom navigation bar when clicking artist option`() {
        mainViewModel.onSearchArtistClick()

        assertEquals(mainViewModel.navigationBarSearchOptionSelected.value, true)
    }

    @Test
    fun `should set content type artist when artist option is clicked`() {
        mainViewModel.onSearchArtistClick()

        assertEquals(mainViewModel.containerViewContentType.value, SearchType.ARTIST)
    }

    @Test
    fun `should hide search options layout when clicking search album option`() {
        mainViewModel.onSearchAlbumClick()

        assertEquals(mainViewModel.searchOptionsLayoutVisibility.value, false)
    }

    @Test
    fun `should select search in bottom navigation bar when clicking album option`() {
        mainViewModel.onSearchAlbumClick()

        assertEquals(mainViewModel.navigationBarSearchOptionSelected.value, true)
    }

    @Test
    fun `should set content type artist when album option is clicked`() {
        mainViewModel.onSearchAlbumClick()

        assertEquals(mainViewModel.containerViewContentType.value, SearchType.ALBUM)
    }
}
