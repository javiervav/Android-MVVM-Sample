package com.android.organizer.presentation.main

import com.android.organizer.presentation.BasePresenter
import com.android.organizer.presentation.search.SearchType

class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun onMenuSearchButtonClick() {
        view.toggleSearchOptionsLayoutVisibility(view.isBottomSheetHidden())
    }

    override fun onSearchArtistClick() {
        view.selectNavigationBarSearchOption()
        view.toggleSearchOptionsLayoutVisibility(false)
        view.navigateToSearch(SearchType.ARTIST)
    }

    override fun onSearchAlbumClick() {
        view.selectNavigationBarSearchOption()
        view.toggleSearchOptionsLayoutVisibility(false)
        view.navigateToSearch(SearchType.ALBUM)
    }
}
