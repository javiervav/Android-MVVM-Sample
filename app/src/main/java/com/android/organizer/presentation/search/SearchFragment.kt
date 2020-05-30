package com.android.organizer.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.organizer.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchSelectMusic.setOnClickListener { Toast.makeText(activity, "hola", Toast.LENGTH_SHORT).show() }
        searchSelectBooks.setOnClickListener { Toast.makeText(activity, "hola2", Toast.LENGTH_SHORT).show() }
    }
}
