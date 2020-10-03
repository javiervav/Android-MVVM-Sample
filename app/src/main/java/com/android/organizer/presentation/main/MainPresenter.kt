package com.android.organizer.presentation.main

import com.android.organizer.presentation.BasePresenter

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
}
