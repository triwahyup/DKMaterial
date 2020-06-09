package com.build.core.material.snackbar

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.build.core.R
import com.build.core.utils.Utils
import com.build.core.utils.invisible
import com.build.core.utils.visible
import com.google.android.material.snackbar.Snackbar

interface SnackbarCustom : BaseSnackbarCustom {
    override val drawableId: Int
        get() = 0

    fun getSnackbar(
        activity: Activity,
        message: String,
        duration: Int=Snackbar.LENGTH_LONG,
        label: String? = null,
        listener: (() -> Unit)? = null
    ) : Snackbar {
        val view = activity.findViewById<View>(android.R.id.content)
        val snackbar = Snackbar.make(view, "", duration)
        snackbar.view.setBackgroundColor(Color.TRANSPARENT)

        val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
        snackbarLayout.setPadding(0, 0, 0, 0)

        val snackView = activity.layoutInflater.inflate(R.layout.snackbar, null)
        snackView.findViewById<TextView>(R.id.message).text = message
        snackView.findViewById<View>(R.id.view).invisible()

        val icon = snackView.findViewById<ImageView>(R.id.icon)
        icon.setImageResource(this.drawableId)
        Utils.setCircleImageToImageView(activity, icon, drawableId, 0, 0)

        label?.let {
            snackView.findViewById<View>(R.id.view).visible()

            val tv_action = snackView.findViewById<TextView>(R.id.action)
            tv_action.text = it
            tv_action.setOnClickListener {
                listener?.invoke()
            }
        }

        snackbarLayout.addView(snackView, 0)
        return snackbar
    }
}

interface BaseSnackbarCustom {
    val drawableId: Int
}