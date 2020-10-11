package com.android.organizer.presentation

import android.os.Bundle
import android.view.View

abstract class BasePresenter<T : BaseContract.View> : BaseContract.Presenter {

    lateinit var view: T

    override fun attachView(view: BaseContract.View) {
        this.view = view as T // TODO Can I get rid of the warning?
    }

    override fun onViewCreated() {
    }
}
