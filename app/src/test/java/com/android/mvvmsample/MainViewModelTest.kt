package com.android.mvvmsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.mvvmsample.presentation.main.MainViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
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
    fun `should change options layout visibility when clicking search button and is hidden`() {
        val observer = mock<Observer<Boolean>>()
        mainViewModel.searchOptionsLayoutVisibility.observeForever(observer)

        mainViewModel.onMenuSearchButtonClick()

        verify(observer).onChanged(true)
    }

    @Test
    fun `should select search in navigation bar and hide bottom sheet when clicking artist option`() {
        val optionsVisibilityObserver = mock<Observer<Boolean>>()
        mainViewModel.searchOptionsLayoutVisibility.observeForever(optionsVisibilityObserver)
        val navBarSearchOptionSelectedObserver = mock<Observer<Boolean>>()
        mainViewModel.navigationBarSearchOptionSelected.observeForever(navBarSearchOptionSelectedObserver)

        mainViewModel.onSearchArtistClick()

        verify(optionsVisibilityObserver).onChanged(false)
        verify(navBarSearchOptionSelectedObserver).onChanged(true)
    }

    @Test
    fun `should select search in navigation bar and hide bottom sheet when clicking album option`() {
        val optionsVisibilityObserver = mock<Observer<Boolean>>()
        mainViewModel.searchOptionsLayoutVisibility.observeForever(optionsVisibilityObserver)
        val navBarSearchOptionSelectedObserver = mock<Observer<Boolean>>()
        mainViewModel.navigationBarSearchOptionSelected.observeForever(navBarSearchOptionSelectedObserver)

        mainViewModel.onSearchAlbumClick()

        verify(optionsVisibilityObserver).onChanged(false)
        verify(navBarSearchOptionSelectedObserver).onChanged(true)
    }
}
