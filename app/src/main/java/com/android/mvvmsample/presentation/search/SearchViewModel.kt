package com.android.mvvmsample.presentation.search

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

    val toggleLoaderVisibility = MutableLiveData<Boolean>()
    val errorLayoutVisibility = MutableLiveData<Boolean>()
    val artistList = MutableLiveData<List<Artist>>()

    fun searchInfo(text: String) {
        if (text.length >= MIN_CHARACTERS) {
            toggleLoaderVisibility.value = true
            viewModelScope.launch {
                val result = withContext(Dispatchers.IO) {
                    getArtistInfoUseCase.execute(text)
                }
                onArtistInfoReceived(result)
            }
        }
    }

    private fun onArtistInfoReceived(result: Result<List<Artist>>) {
        toggleLoaderVisibility.value = false
        if (result is Result.Success) {
            artistList.value = result.value
        } else {
            errorLayoutVisibility.value = true
        }
    }
}
