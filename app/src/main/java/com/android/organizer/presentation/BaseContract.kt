package com.android.organizer.presentation

interface BaseContract {

    interface View

    interface Presenter<in T> {
        fun attachView(view: T)
    }
}
