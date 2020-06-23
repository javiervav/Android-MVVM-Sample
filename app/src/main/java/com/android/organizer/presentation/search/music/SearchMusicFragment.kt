package com.android.organizer.presentation.search.music

import android.os.Bundle
import android.view.View
import com.android.organizer.R
import com.android.organizer.presentation.BaseFragment
import com.android.organizer.utils.extensions.onTextChanged
import kotlinx.android.synthetic.main.fragment_search_music.*

class SearchMusicFragment : BaseFragment<SearchMusicContract.Presenter>(), SearchMusicContract.View {

    override fun getLayoutResource() = R.layout.fragment_search_music

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        searchEt.onTextChanged(callback = { presenter.onKeyTyped(it) }, minimumChars = 3)
    }
}
