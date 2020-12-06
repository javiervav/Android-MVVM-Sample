package com.android.mvvmsample.presentation.search

import android.os.Bundle
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

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val searchViewModel: SearchViewModel by viewModels { viewModelFactory }

    private lateinit var searchListAdapter: SearchListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
        initViews()
    }

    private fun initObservables() {
        searchViewModel.artistList.observe(requireActivity(), Observer { artists ->
            searchListAdapter.submitList(artists)
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
            .onEach { searchViewModel.searchInfo(it.toString()) }
            .launchIn(lifecycleScope)
    }
}
