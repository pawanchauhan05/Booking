package com.app.booking.utilities

import android.view.View

fun View.visibility(flag: Boolean) {
    visibility = if (flag) {
        View.VISIBLE
    } else {
        View.GONE
    }
}