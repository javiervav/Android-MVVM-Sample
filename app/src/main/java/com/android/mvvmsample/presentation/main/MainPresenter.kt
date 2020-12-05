package com.android.mvvmsample.presentation.main

import com.android.mvvmsample.presentation.BasePresenter
import com.android.mvvmsample.presentation.search.SearchType

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
