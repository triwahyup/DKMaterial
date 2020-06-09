package com.build.core.material.toast

import android.app.Activity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.build.core.R

interface ToastCustom : BaseToastCustom {
    override val drawableId: Int
        get() = 0
    override val colorId: Int
        get() = 0

    fun getToast(
        activity: Activity,
        message: String,
        duration: Int=Toast.LENGTH_LONG
    ) : Toast {
        val toast = Toast(activity.applicationContext)
        toast.duration = duration

        val customView = activity.layoutInflater.inflate(R.layout.toast, null)
        customView.findViewById<TextView>(R.id.message).text = message
        customView.findViewById<ImageView>(R.id.icon).setImageResource(this.drawableId)
        customView.findViewById<CardView>(R.id.card_view)
            .setCardBackgroundColor(
                ContextCompat.getColor(
                    activity.applicationContext,
                    this.colorId
                )
            )

        toast.view = customView
        return toast
    }
}

interface BaseToastCustom {
    val drawableId: Int
    val colorId: Int
}