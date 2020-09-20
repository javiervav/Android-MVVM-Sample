package com.android.organizer.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.organizer.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchSelectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchSelectMusic.setOnClickListener { findNavController().navigate(R.id.action_searchFragment_to_searchMusicFragment) }
        searchSelectBooks.setOnClickListener { openBottomSheet() }
    }

    private fun openBottomSheet() {
        val bottomSheet = SearchBottomSheetDialogFragment.newInstance()
        activity?.let {
            bottomSheet.show(it.supportFragmentManager, "")
        }
    }
}
