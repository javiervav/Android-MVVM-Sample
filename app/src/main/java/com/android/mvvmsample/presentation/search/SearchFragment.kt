package com.android.mvvmsample.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mvvmsample.R
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchType = arguments?.getSerializable(SEARCH_TYPE_ARG) as SearchType?

        initObservables()
        initViews()
    }

    private fun initObservables() {
        searchViewModel.searchItemList.observe(requireActivity(), Observer { searchItems ->
            searchListAdapter.submitList(searchItems)
        })

        searchViewModel.errorLayoutVisibility.observe(requireActivity(), Observer {
            Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show()
        })

        searchViewModel.toggleLoaderVisibility.observe(requireActivity(), Observer { visible ->
            if (visible) {
                searchPlaceholderLayout.visibility = View.VISIBLE
                searchRv.visibility = View.GONE
            } else {
                searchRv.visibility = View.VISIBLE
                searchPlaceholderLayout.visibility = View.GONE
            }
        })
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
