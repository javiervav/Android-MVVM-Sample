package com.android.organizer.utils.extensions

import android.content.res.Resources

val Float.toPx: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

val Float.toDp: Float
    get() = (this / Resources.getSystem().displayMetrics.density)
