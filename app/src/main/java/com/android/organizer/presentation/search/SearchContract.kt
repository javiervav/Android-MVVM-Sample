package com.android.organizer.presentation.search

import com.android.domain.models.Artist
import com.android.organizer.presentation.BaseContract

interface SearchContract {

    interface View : BaseContract.View {
        fun getSearchType(): SearchType?
        fun initViews()
        fun openSearchDialog()
        fun updateArtistList(artists: List<Artist>)
    }

    interface Presenter : BaseContract.Presenter {
        fun onSearchFieldClicked()
        fun searchInfo(text: String)
    }
}
