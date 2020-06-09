package com.build.core.material.dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Paint
import android.os.Handler
import android.view.Window
import com.build.core.R
import com.build.core.utils.gone
import com.build.core.utils.visible
import kotlinx.android.synthetic.main.popup_custom_confirm.*
import kotlinx.android.synthetic.main.popup_custom_confirm.leftBtn
import kotlinx.android.synthetic.main.popup_custom_confirm.message
import kotlinx.android.synthetic.main.popup_custom_confirm.rightBtn
import kotlinx.android.synthetic.main.popup_no_internet.*

interface ConfirmCustom : BaseCustomConfirm {
    override val drawableBackground: Int
        get() = 0
    override val drawableShapeButton: Int
        get() = 0
    override val drawableIconShape: Int
        get() = 0

    fun getConfirm(
        activity: Activity,
        message: String?,
        actionX: String?,
        actionY: String?,
        listenerX: (() -> Unit)?,
        listenerY: (() -> Unit)?
    ) : Dialog {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.popup_custom_confirm)
        dialog.setCancelable(false)

        dialog.backgroundImage.setImageResource(this.drawableBackground)
        dialog.iconShape.setBackgroundResource(this.drawableIconShape)
        dialog.message.text = message
        actionX?.let {
            dialog.leftBtn.text = it
            dialog.leftBtn.setBackgroundResource(this.drawableShapeButton)
            dialog.leftBtn.setOnClickListener {
                listenerX?.invoke()
            }
        }
        actionY?.let {
            dialog.rightBtn.text = it
            dialog.rightBtn.setBackgroundResource(this.drawableShapeButton)
            dialog.rightBtn.setOnClickListener {
                listenerY?.invoke()
            }
        }
        return dialog
    }

    fun getShowPrivacy(
        activity: Activity,
        message: String?,
        actionX: String?,
        actionY: String?,
        listenerX: (() -> Unit)?,
        listenerY: (() -> Unit)?
    ) : Dialog {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.popup_privacy_and_policy)
        dialog.setCancelable(false)

        dialog.message.text = message
        actionX?.let {
            dialog.leftBtn.text = it
            dialog.leftBtn.paintFlags = dialog.leftBtn.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            dialog.leftBtn.setOnClickListener {
                listenerX?.invoke()
            }
        }
        actionY?.let {
            dialog.rightBtn.text = it
            dialog.rightBtn.paintFlags = dialog.rightBtn.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            dialog.rightBtn.setOnClickListener {
                listenerY?.invoke()
            }
        }
        return dialog
    }

    fun getShowNointernet(activity: Activity, listener: (() -> Unit)?) : Dialog {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.popup_no_internet)
        dialog.setCancelable(true)

        dialog.layoutProgressBar.gone()
        dialog.layoutErrorDesc.visible()
        dialog.retry.setOnClickListener {
            dialog.layoutProgressBar.visible()
            dialog.layoutErrorDesc.gone()

            Handler().postDelayed(Runnable {
                listener?.invoke()

                dialog.layoutProgressBar.gone()
                dialog.layoutErrorDesc.visible()
            }, 1000)

        }
        return dialog
    }
}

interface BaseCustomConfirm {
    val drawableBackground: Int
    val drawableShapeButton: Int
    val drawableIconShape: Int
}