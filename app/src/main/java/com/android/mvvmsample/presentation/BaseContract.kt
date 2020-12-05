package com.android.mvvmsample.presentation

import kotlinx.coroutines.CoroutineScope

interface BaseContract {

    interface View

    interface Presenter {
        fun attachView(view: View, scope: CoroutineScope)
    }
}
