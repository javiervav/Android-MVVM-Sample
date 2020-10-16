package com.android.organizer.presentation.search.dialog

import com.android.organizer.R
import com.android.organizer.presentation.BaseDialogFragment

class SearchDialogFragment : BaseDialogFragment<SearchDialogContract.Presenter>(), SearchDialogContract.View {

    companion object {
        fun newInstance(): SearchDialogFragment {
            return SearchDialogFragment()
        }
    }

    override fun getLayoutResource(): Int = R.layout.fragment_search_dialog
}
