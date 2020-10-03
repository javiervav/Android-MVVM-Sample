package com.android.organizer.presentation.main

import com.android.organizer.presentation.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun isBottomSheetExpanded(): Boolean
        fun expandBottomSheet()
        fun collapseBottomSheet()
        fun moveBottomSheetVertically(slideOffset: Float)
    }

    interface Presenter : BaseContract.Presenter {
        fun onBottomSheetSlide(slideOffset: Float)
        fun onSearchButtonClick()
    }
}
