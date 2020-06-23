package com.android.organizer.presentation.search.music

import com.android.organizer.presentation.BaseContract

interface SearchMusicContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter {
        fun onKeyTyped(text: String)
    }
}
