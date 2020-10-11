package com.android.organizer.presentation.search

import android.widget.Toast
import com.android.organizer.R
import com.android.organizer.presentation.BaseFragment
import com.google.android.material.internal.TextWatcherAdapter
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment<SearchContract.Presenter>(), SearchContract.View {

    companion object {
        private const val SEARCH_TYPE = "searchType"
    }

    override fun getLayoutResource() = R.layout.fragment_search

    override fun getSearchType(): SearchType? = arguments?.getSerializable(SEARCH_TYPE) as? SearchType

    override fun initViews() {
        addSearchEditTextListener()
    }

    private fun addSearchEditTextListener() {
        searchEt.addTextChangedListener(object : TextWatcherAdapter() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Toast.makeText(activity, s, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
