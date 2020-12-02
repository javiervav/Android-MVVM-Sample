package com.android.organizer.presentation

abstract class BasePresenter<T : BaseContract.View> : BaseContract.Presenter {

    lateinit var view: T

    override fun attachView(view: BaseContract.View) {
        this.view = view as T // TODO Can I get rid of the warning?
    }
}
