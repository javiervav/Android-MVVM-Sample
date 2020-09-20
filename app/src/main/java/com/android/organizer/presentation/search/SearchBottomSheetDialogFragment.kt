package com.android.organizer.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.organizer.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(): SearchBottomSheetDialogFragment {
            return SearchBottomSheetDialogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.search_bottom_sheet, container, false)
}
