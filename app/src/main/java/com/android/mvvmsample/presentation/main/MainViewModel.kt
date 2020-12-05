package com.android.mvvmsample.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val searchOptionsLayoutVisibility = MutableLiveData<Boolean>()
    val navigationBarSearchOptionSelected = MutableLiveData<Boolean>()

    fun onMenuSearchButtonClick() {
        searchOptionsLayoutVisibility.value = searchOptionsLayoutVisibility.value != true
    }

    fun onSearchArtistClick() {
        navigationBarSearchOptionSelected.value = true
        searchOptionsLayoutVisibility.value = searchOptionsLayoutVisibility.value != true
    }

    fun onSearchAlbumClick() {
        navigationBarSearchOptionSelected.value = true
        searchOptionsLayoutVisibility.value = searchOptionsLayoutVisibility.value != true
    }
}
