package com.android.organizer.presentation.main

import com.android.organizer.presentation.BasePresenter
import com.android.organizer.presentation.search.SearchType

class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun onBottomSheetSlide(slideOffset: Float) {
        view.moveBottomSheetVertically(slideOffset)
    }

    override fun onSearchButtonClick() {
        if (view.isBottomSheetExpanded()) {
            view.collapseBottomSheet()
        } else {
            view.expandBottomSheet()
        }
    }

    override fun onSearchArtistClick() {
        view.navigateToSearch(SearchType.ARTIST)
    }

    override fun onSearchAlbumClick() {
        view.navigateToSearch(SearchType.ALBUM)
    }
}
