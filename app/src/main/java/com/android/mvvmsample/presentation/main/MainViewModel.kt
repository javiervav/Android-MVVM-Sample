package com.android.mvvmsample.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.mvvmsample.presentation.search.SearchType

class MainViewModel : ViewModel() {

    private val _searchOptionsLayoutVisibility = MutableLiveData<Boolean>()
    val searchOptionsLayoutVisibility: LiveData<Boolean> = _searchOptionsLayoutVisibility

    private val _navigationBarSearchOptionSelected = MutableLiveData<Boolean>()
    val navigationBarSearchOptionSelected: LiveData<Boolean> = _navigationBarSearchOptionSelected

    private val _containerViewContentType = MutableLiveData<SearchType>()
    val containerViewContentType: LiveData<SearchType> = _containerViewContentType

    fun onMenuSearchButtonClick() {
        toggleSearchOptionsLayoutVisibility()
    }

    fun onSearchArtistClick() {
        onMenuOptionClick()
        _containerViewContentType.value = SearchType.ARTIST
    }

    fun onSearchAlbumClick() {
        onMenuOptionClick()
        _containerViewContentType.value = SearchType.ALBUM
    }

    private fun toggleSearchOptionsLayoutVisibility() {
        _searchOptionsLayoutVisibility.value = _searchOptionsLayoutVisibility.value != true
    }

    private fun onMenuOptionClick() {
        _navigationBarSearchOptionSelected.value = true
        _searchOptionsLayoutVisibility.value = false
    }
}
