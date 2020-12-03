package com.android.organizer.presentation

import kotlinx.coroutines.CoroutineScope

abstract class BasePresenter<T : BaseContract.View> : BaseContract.Presenter {

    lateinit var view: T
    lateinit var scope: CoroutineScope

    override fun attachView(view: BaseContract.View, scope: CoroutineScope) {
        this.view = view as T // TODO Can I get rid of the warning?
        this.scope = scope
    }
}
