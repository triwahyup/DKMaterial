package com.build.core.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Extension method use to display a [Snackbar] message to the user.
 */
fun View.displaySnakbar(message: String, duration: Int=Snackbar.LENGTH_SHORT) : Snackbar {
    val snackbar = Snackbar.make(this, message, duration)
    snackbar.show()
    return snackbar
}

fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

fun View.gone() {
    if(visibility != View.GONE) {
        visibility = View.GONE
    }
}