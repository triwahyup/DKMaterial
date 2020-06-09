package com.build.core

import android.app.Activity
import android.app.Dialog
import android.graphics.Paint
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.build.core.material.dialog.ConfirmCustomImpl
import com.build.core.material.snackbar.SnackbarCustomImpl
import com.build.core.material.toast.ToastCustomImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.popup_no_internet.*
import kotlinx.android.synthetic.main.popup_privacy_and_policy.*


abstract class BaseActivity : AppCompatActivity(),
    BaseToastImpl, BaseSnackbarImpl, BasePopupImpl {

    private var snackbar: Snackbar? = null
    private var nDialog: Dialog? = null

    override fun showToast(type: String, message: String, duration: Int) {
        ToastCustomImpl(type).getToast(this, message, duration).show()
    }

    override fun showSnackbar(
        type: String,
        message: String,
        duration: Int,
        label: String?,
        listener: (() -> Unit)?
    ): Snackbar? {
        snackbar = SnackbarCustomImpl(type).getSnackbar(this, message, duration)
        snackbar?.show()
        return snackbar
    }

    override fun popupNoInternetAccess(listener: (() -> Unit)?) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.popup_no_internet)
        dialog.setCancelable(true)

        dialog.retry.setOnClickListener {
            listener?.invoke()
        }
        dialog.show()
    }

    override fun popupPrivacyAndPolicy(
        message: String?,
        actionX: String?,
        actionY: String?,
        listenerX: (() -> Unit)?,
        listenerY: (() -> Unit)?
    ) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.popup_privacy_and_policy)
        dialog.setCancelable(true)

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
        dialog.show()
    }

    override fun customPopupConfirm(
        type: String?,
        message: String?,
        actionX: String?,
        actionY: String?,
        listenerX: (() -> Unit)?,
        listenerY: (() -> Unit)?
    ): Dialog? {
        nDialog = ConfirmCustomImpl(type).getConfirm(this, message, actionX, actionY, listenerX, listenerY)
        nDialog?.show()
        return nDialog
    }
}

interface BaseToastImpl {
    fun showToast(type: String, message: String, duration: Int= Toast.LENGTH_LONG)
}

interface BaseSnackbarImpl {
    fun showSnackbar(
        type: String,
        message: String,
        duration: Int=Snackbar.LENGTH_LONG,
        label: String? = null,
        listener: (() -> Unit)? = null
    ) : Snackbar?
}

interface BasePopupImpl {
    fun popupNoInternetAccess(listener: (() -> Unit)?)
    fun popupPrivacyAndPolicy(
        message: String?,
        actionX: String?,
        actionY: String?,
        listenerX: (() -> Unit)?,
        listenerY: (() -> Unit)?)
    fun customPopupConfirm(
        type: String?,
        message: String?,
        actionX: String?,
        actionY: String?,
        listenerX: (() -> Unit)?,
        listenerY: (() -> Unit)?) : Dialog?
}