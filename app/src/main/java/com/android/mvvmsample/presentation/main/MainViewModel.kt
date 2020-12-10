package com.android.mvvmsample.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _searchOptionsLayoutVisibility = MutableLiveData<Boolean>()
    val searchOptionsLayoutVisibility: LiveData<Boolean> = _searchOptionsLayoutVisibility

    private val _navigationBarSearchOptionSelected = MutableLiveData<Boolean>()
    val navigationBarSearchOptionSelected: LiveData<Boolean> = _navigationBarSearchOptionSelected

    fun onMenuSearchButtonClick() {
        toggleSearchOptionsLayoutVisibility()
    }

    fun onSearchArtistClick() {
        onMenuOptionClick()
    }

    fun onSearchAlbumClick() {
        onMenuOptionClick()
    }

    private fun toggleSearchOptionsLayoutVisibility() {
        _searchOptionsLayoutVisibility.value = _searchOptionsLayoutVisibility.value != true
    }

    private fun onMenuOptionClick() {
        _navigationBarSearchOptionSelected.value = true
        _searchOptionsLayoutVisibility.value = false
    }
}
