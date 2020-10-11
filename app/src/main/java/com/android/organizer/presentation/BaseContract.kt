package com.android.organizer.presentation

import android.os.Bundle

interface BaseContract {

    interface View

    interface Presenter {
        fun attachView(view: View)
        fun onViewCreated()
    }
}
