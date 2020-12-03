package com.android.organizer.presentation.search

import com.android.domain.Result
import com.android.domain.models.Artist
import com.android.domain.usecases.GetArtistInfoUseCase
import com.android.organizer.presentation.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchPresenter(
    private val getArtistInfoUseCase: GetArtistInfoUseCase
) : BasePresenter<SearchContract.View>(), SearchContract.Presenter {

    companion object {
        private const val MIN_CHARACTERS = 3
    }

    override fun searchInfo(text: String) {
        if (text.length >= MIN_CHARACTERS) {
            view.toggleLoader(true)
            scope.launch {
                val result = withContext(Dispatchers.IO) {
                    getArtistInfoUseCase.execute(text)
                }
                onArtistInfoReceived(result)
            }
        }
    }

    private fun onArtistInfoReceived(result: Result<List<Artist>>) {
        view.toggleLoader(false)
        if (result is Result.Success) {
            view.updateArtistList(result.value)
        } else {
            view.showError()
        }
    }
}
