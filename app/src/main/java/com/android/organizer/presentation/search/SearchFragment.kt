package com.android.organizer.presentation.search

import android.widget.Toast
import com.android.domain.models.Artist
import com.android.organizer.R
import com.android.organizer.presentation.BaseFragment
import com.android.organizer.presentation.search.dialog.SearchDialogFragment
import com.google.android.material.internal.TextWatcherAdapter
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment<SearchContract.Presenter>(), SearchContract.View {

    companion object {
        private const val SEARCH_TYPE = "searchType"
        private const val SEARCH_DIALOG_FRAGMENT_TAG = "searchDialogFragmentTag"
    }

    override fun getLayoutResource() = R.layout.fragment_search

    override fun getSearchType(): SearchType? = arguments?.getSerializable(SEARCH_TYPE) as? SearchType

    override fun initViews() {
        searchEt.setOnClickListener { presenter.onSearchFieldClicked() }
//        addSearchEditTextListener()
    }

    override fun openSearchDialog() {
        activity?.supportFragmentManager?.beginTransaction()?.let { fragmentTransaction ->
            val previousDialog = activity?.supportFragmentManager?.findFragmentByTag(SEARCH_DIALOG_FRAGMENT_TAG)
            if (previousDialog != null) {
                fragmentTransaction.remove(previousDialog)
            }
            fragmentTransaction.addToBackStack(null)
            SearchDialogFragment.newInstance().show(fragmentTransaction, SEARCH_DIALOG_FRAGMENT_TAG)
        }
    }

    override fun updateArtistList(artists: List<Artist>) {
        Toast.makeText(activity, artists.size.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun addSearchEditTextListener() {
        searchEt.addTextChangedListener(object : TextWatcherAdapter() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                presenter.searchInfo(s.toString())
            }
        })
    }
}
