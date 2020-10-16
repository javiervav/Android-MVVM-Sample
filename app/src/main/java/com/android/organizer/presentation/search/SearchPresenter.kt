package com.android.organizer.presentation.search

import com.android.domain.usecases.GetArtistInfoUseCase
import com.android.organizer.presentation.BasePresenter

class SearchPresenter(
    private val getArtistInfoUseCase: GetArtistInfoUseCase
) : BasePresenter<SearchContract.View>(), SearchContract.Presenter {

    companion object {
        private const val MIN_CHARACTERS = 3
    }

    override fun onViewCreated() {
        super.onViewCreated()
        view.initViews()
    }

    override fun onSearchFieldClicked() {
        view.openSearchDialog()
    }

    override fun searchInfo(text: String) {
        if (text.length >= MIN_CHARACTERS) {
            getArtistInfoUseCase.execute(text = text, callback = { artistList -> view.updateArtistList(artistList) })
        }
    }
}
