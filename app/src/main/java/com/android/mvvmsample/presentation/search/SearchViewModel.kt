package com.android.mvvmsample.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.Result
import com.android.domain.models.SearchItem
import com.android.domain.usecases.GetAlbumInfoUseCase
import com.android.domain.usecases.GetArtistInfoUseCase
import com.android.mvvmsample.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val getArtistInfoUseCase: GetArtistInfoUseCase,
    private val getAlbumInfoUseCase: GetAlbumInfoUseCase
) : ViewModel() {

    companion object {
        private const val MIN_CHARACTERS = 3
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _errorLayoutVisibility = MutableLiveData<Boolean>()
    val errorLayoutVisibility: LiveData<Boolean> = _errorLayoutVisibility
    private val _searchItemList = MutableLiveData<List<SearchItem>>()
    val searchItemList: LiveData<List<SearchItem>> = _searchItemList

    fun searchInfo(text: String, searchType: SearchType) {
        if (text.length >= MIN_CHARACTERS) {
            _isLoading.value = true
            viewModelScope.launch {
                val result = withContext(ioDispatcher) {
                    when (searchType) {
                        SearchType.ARTIST -> getArtistInfoUseCase.execute(text)
                        SearchType.ALBUM -> getAlbumInfoUseCase.execute(text)
                    }
                }
                onInfoReceived(result)
            }
        }
    }

    private fun onInfoReceived(result: Result<List<SearchItem>>) {
        _isLoading.value = false
        if (result is Result.Success) {
            _searchItemList.value = result.value
        } else {
            _errorLayoutVisibility.value = true
        }
    }
}
