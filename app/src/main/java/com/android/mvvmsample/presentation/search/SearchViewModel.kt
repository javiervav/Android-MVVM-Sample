package com.android.mvvmsample.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.Result
import com.android.domain.models.Artist
import com.android.domain.usecases.GetArtistInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getArtistInfoUseCase: GetArtistInfoUseCase
) : ViewModel() {

    companion object {
        private const val MIN_CHARACTERS = 3
    }

    private val _toggleLoaderVisibility = MutableLiveData<Boolean>()
    val toggleLoaderVisibility: LiveData<Boolean> = _toggleLoaderVisibility
    private val _errorLayoutVisibility = MutableLiveData<Boolean>()
    val errorLayoutVisibility: LiveData<Boolean> = _errorLayoutVisibility
    private val _artistList = MutableLiveData<List<Artist>>()
    val artistList: LiveData<List<Artist>> = _artistList

    fun searchInfo(text: String) {
        if (text.length >= MIN_CHARACTERS) {
            _toggleLoaderVisibility.value = true
            viewModelScope.launch {
                val result = withContext(Dispatchers.IO) {
                    getArtistInfoUseCase.execute(text)
                }
                onArtistInfoReceived(result)
            }
        }
    }

    private fun onArtistInfoReceived(result: Result<List<Artist>>) {
        _toggleLoaderVisibility.value = false
        if (result is Result.Success) {
            _artistList.value = result.value
        } else {
            _errorLayoutVisibility.value = true
        }
    }
}
