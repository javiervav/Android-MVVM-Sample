package com.android.organizer.presentation.search

import com.android.organizer.presentation.BasePresenter

class SearchPresenter : BasePresenter<SearchContract.View>(), SearchContract.Presenter {

    override fun onViewCreated() {
        super.onViewCreated()
        view.initViews()
    }
}
