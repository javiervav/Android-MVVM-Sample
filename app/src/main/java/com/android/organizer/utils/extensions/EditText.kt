package com.android.organizer.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

// TODO: Can I make it inline? Check inline, noninline, crossinline
fun EditText.onTextChanged(callback: (String) -> Unit, minimumChars: Int = 1) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let { text ->
                if (text.trim().length >= minimumChars) {
                    callback(text.toString())
                }
            }
        }
    })
}
