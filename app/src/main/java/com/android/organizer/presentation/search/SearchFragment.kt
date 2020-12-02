package com.android.organizer.presentation.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.domain.models.Artist
import com.android.organizer.R
import com.android.organizer.presentation.BaseFragment
import com.android.organizer.utils.extensions.onTextChangeDebounced
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@FlowPreview
@ExperimentalCoroutinesApi
class SearchFragment : BaseFragment<SearchContract.Presenter>(), SearchContract.View {

    companion object {
        private const val SEARCH_TYPE = "searchType"
    }

    private lateinit var searchListAdapter: SearchListAdapter

    override fun getLayoutResource() = R.layout.fragment_search

    override fun getSearchType(): SearchType? = arguments?.getSerializable(SEARCH_TYPE) as? SearchType

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun updateArtistList(artists: List<Artist>) {
        searchListAdapter.submitList(artists)
    }

    override fun showError() {
        Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show()
    }

    override fun toggleLoader(visible: Boolean) {
        if (visible) {
            searchPlaceholderLayout.visibility = View.VISIBLE
            searchRv.visibility = View.GONE
        } else {
            searchRv.visibility = View.VISIBLE
            searchPlaceholderLayout.visibility = View.GONE
        }
    }

    private fun initViews() {
        initList()
        addSearchEditTextListener()
    }

    private fun initList() {
        searchListAdapter = SearchListAdapter()
        with(searchRv) {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = searchListAdapter
        }
    }

    private fun addSearchEditTextListener() {
        searchEt.onTextChangeDebounced()
            .filterNot { it.isNullOrBlank() }
            .onEach { presenter.searchInfo(it.toString()) }
            .launchIn(lifecycleScope)
    }
}
