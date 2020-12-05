package com.android.mvvmsample.presentation.search

import com.android.domain.models.Artist
import com.android.mvvmsample.presentation.BaseContract

interface SearchContract {

    interface View : BaseContract.View {
        fun getSearchType(): SearchType?
        fun updateArtistList(artists: List<Artist>)
        fun showError()
        fun toggleLoader(visible: Boolean)
    }

    interface Presenter : BaseContract.Presenter {
        fun searchInfo(text: String)
    }
}
