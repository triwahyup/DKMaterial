package com.build.core.material.snackbar

import android.app.Activity
import android.view.View
import com.build.core.R
import com.build.core.utils.displaySnakbar
import com.google.android.material.snackbar.Snackbar


class SnackbarCustomImpl(pType: String) : SnackbarCustom {
    private val type = pType

    override fun getSnackbar(
        activity: Activity,
        message: String,
        duration: Int,
        label: String?,
        listener: (() -> Unit)?
    ): Snackbar {
        when(type) {
            "success" -> {
                return SnackbarSuccess.getSnackbar(activity, message, duration, label, listener)
            }
            "error" -> {
                return SnackbarError.getSnackbar(activity, message, duration, label, listener)
            }
            "info" -> {
                return SnackbarInfo.getSnackbar(activity, message, duration, label, listener)
            }
            "warning" -> {
                return SnackbarWarning.getSnackbar(activity, message, duration, label, listener)
            }
            "inbox" -> {
                return SnackbarInbox.getSnackbar(activity, message, duration, label, listener)
            }
        }
        return activity.findViewById<View>(android.R.id.content).displaySnakbar(message, Snackbar.LENGTH_LONG)
    }
}

object SnackbarSuccess : SnackbarCustom {
    override val drawableId: Int
        get() = R.drawable.ic_png_success
}

object SnackbarError : SnackbarCustom {
    override val drawableId: Int
        get() = R.drawable.ic_png_error
}

object SnackbarInfo : SnackbarCustom {
    override val drawableId: Int
        get() = R.drawable.ic_png_info
}

object SnackbarWarning : SnackbarCustom {
    override val drawableId: Int
        get() = R.drawable.ic_png_warning
}

object SnackbarInbox : SnackbarCustom {
    override val drawableId: Int
        get() = R.drawable.ic_png_inbox
}