package com.android.organizer.presentation

interface BaseContract {

    interface View

    interface Presenter {
        fun attachView(view: View)
    }
}
