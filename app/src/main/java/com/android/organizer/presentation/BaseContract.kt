package com.android.organizer.presentation

import kotlinx.coroutines.CoroutineScope

interface BaseContract {

    interface View

    interface Presenter {
        fun attachView(view: View, scope: CoroutineScope)
    }
}
