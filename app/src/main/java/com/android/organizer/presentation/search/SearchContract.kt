package com.android.organizer.presentation.search

import com.android.organizer.presentation.BaseContract

interface SearchContract {

    interface View : BaseContract.View {
        fun getSearchType(): SearchType?
        fun initViews()
    }

    interface Presenter : BaseContract.Presenter {

    }
}
