package com.android.organizer.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onStart

private const val DEBOUNCE_TIME = 500L

@FlowPreview
@ExperimentalCoroutinesApi
fun EditText.onTextChangeDebounced(): Flow<CharSequence?> = callbackFlow {
    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            offer(s)
        }

        override fun afterTextChanged(s: Editable?) = Unit
    }
    addTextChangedListener(textWatcher)
    awaitClose { removeTextChangedListener(textWatcher) }
}.debounce(DEBOUNCE_TIME).onStart { emit(text) }
