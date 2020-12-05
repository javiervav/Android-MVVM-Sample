package com.android.mvvmsample.presentation.main

import com.android.mvvmsample.presentation.BaseContract
import com.android.mvvmsample.presentation.search.SearchType

interface MainContract {

    interface View : BaseContract.View {
        fun isBottomSheetHidden(): Boolean
        fun toggleSearchOptionsLayoutVisibility(showOptions: Boolean)
        fun selectNavigationBarSearchOption()
        fun navigateToSearch(searchType: SearchType)
    }

    interface Presenter : BaseContract.Presenter {
        fun onMenuSearchButtonClick()
        fun onSearchArtistClick()
        fun onSearchAlbumClick()
    }
}
