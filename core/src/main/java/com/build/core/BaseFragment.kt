package com.build.core

import android.app.Dialog
import android.graphics.Paint
import android.view.View
import android.view.Window
import androidx.fragment.app.Fragment
import com.build.core.material.dialog.ConfirmCustomImpl
import com.build.core.material.snackbar.SnackbarCustomImpl
import com.build.core.material.toast.ToastCustomImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.popup_no_internet.*
import kotlinx.android.synthetic.main.popup_privacy_and_policy.*

abstract class BaseFragment : Fragment(),
    BaseToastImpl, BaseSnackbarImpl, BasePopupImpl {

    private var snackbar: Snackbar? = null
    private var nDialog: Dialog? = null
    lateinit var nView: View

    override fun showToast(type: String, message: String, duration: Int) {
        activity?.let {
            ToastCustomImpl(type).getToast(it, message, duration).show()
        } ?: throw Exception("ACTIVITY NOT FOUND.")
    }

    override fun showSnackbar(
        type: String,
        message: String,
        duration: Int,
        label: String?,
        listener: (() -> Unit)?
    ) : Snackbar? {
        activity?.let {
            snackbar = SnackbarCustomImpl(type).getSnackbar(it, message, duration)
            snackbar?.show()
            return snackbar
        }
        return null
    }

    override fun popupNoInternetAccess(listener: (() -> Unit)?) {
        val dialog = Dialog(nView.context)
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
        val dialog = Dialog(nView.context)
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
        activity?.let {
            nDialog = ConfirmCustomImpl(type).getConfirm(it, message, actionX, actionY, listenerX, listenerY)
            nDialog?.show()
            return nDialog
        }
        return null
    }
}