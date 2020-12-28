package com.android.mvvmsample.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mvvmsample.databinding.FragmentSearchBinding
import com.android.mvvmsample.utils.extensions.onTextChangeDebounced
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class SearchFragment : DaggerFragment() {

    companion object {
        const val SEARCH_TYPE_ARG = "searchTypeArg"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val searchViewModel: SearchViewModel by viewModels { viewModelFactory }

    private lateinit var searchListAdapter: SearchListAdapter
    private var searchType: SearchType? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        FragmentSearchBinding.inflate(inflater, container, false).apply {
            viewModel = searchViewModel
            lifecycleOwner = viewLifecycleOwner
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchType = arguments?.getSerializable(SEARCH_TYPE_ARG) as SearchType?

        initViews()
    }

    private fun initViews() {
        initList()
        addSearchEditTextListener()
    }

    private fun initList() {
        searchType?.let { searchType ->
            searchListAdapter = SearchListAdapter(searchType)
            with(searchRv) {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = searchListAdapter
            }
        }
    }

    private fun addSearchEditTextListener() {
        searchType?.let { searchType ->
            searchEt.onTextChangeDebounced()
                .filterNot { it.isNullOrBlank() }
                .onEach {
                    searchViewModel.searchInfo(it.toString(), searchType)
                }
                .launchIn(lifecycleScope)
        }
    }
}
