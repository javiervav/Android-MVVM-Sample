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
import kotlinx.coroutines.Job
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

    private var searchJob: Job? = null
    private lateinit var searchType: SearchType
    private lateinit var text: String
    private var offset: Int = 0

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _errorLayoutVisibility = MutableLiveData<Boolean>()
    val errorLayoutVisibility: LiveData<Boolean> = _errorLayoutVisibility
    private val _searchItemList = MutableLiveData<List<SearchItem>>()
    val searchItemList: LiveData<List<SearchItem>> = _searchItemList

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

    fun searchInfo(text: String, searchType: SearchType) {
        if (text.length >= MIN_CHARACTERS) {
            this.searchType = searchType
            this.text = text
            _isLoading.value = true
            offset = 0
            performSearch(DataUpdateType.REFRESH)
        }
    }

    fun loadMore() {
        if (searchJob?.isActive == true) return
        performSearch(DataUpdateType.ADD)
    }

    private fun performSearch(updateType: DataUpdateType) {
        searchJob = viewModelScope.launch {
            val result = withContext(ioDispatcher) {
                when (searchType) {
                    SearchType.ARTIST -> getArtistInfoUseCase.execute(text, offset)
                    SearchType.ALBUM -> getAlbumInfoUseCase.execute(text, offset)
                }
            }
            updateList(result, updateType)
        }
    }

    private fun updateList(result: Result<List<SearchItem>>, updateType: DataUpdateType) {
        _isLoading.value = false
        if (result is Result.Success) {
            offset = if (updateType == DataUpdateType.REFRESH) result.value.size else offset + result.value.size
            _searchItemList.value =
                if (updateType == DataUpdateType.REFRESH) result.value
                else _searchItemList.value?.plus(result.value) ?: result.value
            _errorLayoutVisibility.value = false
        } else {
            _errorLayoutVisibility.value = true
        }
    }

    enum class DataUpdateType {
        REFRESH, ADD
    }
}
