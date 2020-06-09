package com.build.core.material.dialog

import android.app.Activity
import android.app.Dialog
import android.view.Window
import com.build.core.R
import kotlinx.android.synthetic.main.popup_custom_confirm.*

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
        dialog.setCancelable(true)

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
}

interface BaseCustomConfirm {
    val drawableBackground: Int
    val drawableShapeButton: Int
    val drawableIconShape: Int

}