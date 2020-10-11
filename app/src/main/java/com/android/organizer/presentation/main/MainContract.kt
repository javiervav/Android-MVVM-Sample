package com.android.organizer.presentation.main

import com.android.organizer.presentation.BaseContract
import com.android.organizer.presentation.search.SearchType

interface MainContract {

    interface View : BaseContract.View {
        fun isBottomSheetExpanded(): Boolean
        fun expandBottomSheet()
        fun collapseBottomSheet()
        fun moveBottomSheetVertically(slideOffset: Float)
        fun navigateToSearch(searchType: SearchType)
    }

    interface Presenter : BaseContract.Presenter {
        fun onBottomSheetSlide(slideOffset: Float)
        fun onSearchButtonClick()
        fun onSearchArtistClick()
        fun onSearchAlbumClick()
    }
}
