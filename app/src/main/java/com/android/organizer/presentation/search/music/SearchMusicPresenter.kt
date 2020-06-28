package com.android.organizer.presentation.search.music

import com.android.domain.usecases.GetMusicUseCase
import com.android.organizer.presentation.BasePresenter

class SearchMusicPresenter(
    private val getMusicUseCase: GetMusicUseCase
) : BasePresenter<SearchMusicContract.View>(), SearchMusicContract.Presenter {

    override fun onKeyTyped(text: String) {
        getMusicInfo(text)
    }

    private fun getMusicInfo(text: String) {
        val musicInfo = getMusicUseCase.execute(text) {

        }
    }
}
