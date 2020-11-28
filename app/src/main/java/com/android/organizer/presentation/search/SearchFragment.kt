package com.android.organizer.presentation.search

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
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

    override fun getLayoutResource() = R.layout.fragment_search

    override fun getSearchType(): SearchType? = arguments?.getSerializable(SEARCH_TYPE) as? SearchType

    override fun initViews() {
        addSearchEditTextListener()
    }

    override fun updateArtistList(artists: List<Artist>) {
        Toast.makeText(activity, artists.size.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun addSearchEditTextListener() {
        searchEt.onTextChangeDebounced()
            .filterNot { it.isNullOrBlank() }
            .onEach { presenter.searchInfo(it.toString()) }
            .launchIn(lifecycleScope)
    }
}
