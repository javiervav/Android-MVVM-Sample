package com.android.organizer.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.organizer.R

class SearchFragment : Fragment() {

    companion object {
        private const val SEARCH_TYPE = "searchType"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val searchType = arguments?.getSerializable(SEARCH_TYPE) as? SearchType
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
}
