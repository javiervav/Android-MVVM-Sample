package com.android.mvvmsample.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val searchOptionsLayoutVisibility = MutableLiveData<Boolean>()
    val navigationBarSearchOptionSelected = MutableLiveData<Boolean>()

    fun onMenuSearchButtonClick() {
        toggleSearchOptionsLayoutVisibility()
    }

    fun onSearchArtistClick() {
        navigationBarSearchOptionSelected.value = true
        toggleSearchOptionsLayoutVisibility()
    }

    fun onSearchAlbumClick() {
        navigationBarSearchOptionSelected.value = true
        toggleSearchOptionsLayoutVisibility()
    }

    private fun toggleSearchOptionsLayoutVisibility() {
        searchOptionsLayoutVisibility.value = searchOptionsLayoutVisibility.value != true
    }
}
