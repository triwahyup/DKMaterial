package com.build.core

import android.app.Dialog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.build.core.material.dialog.ConfirmCustomImpl
import com.build.core.material.snackbar.SnackbarCustomImpl
import com.build.core.material.toast.ToastCustomImpl
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity(),
    BaseToastImpl, BaseSnackbarImpl, BasePopupImpl {

    private var snackbar: Snackbar? = null
    private var nDialog: Dialog? = null

    /** TOAST SNACKBAR MATERIAL */
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
        snackbar = SnackbarCustomImpl(type).getSnackbar(this, message, duration, label, listener)
        snackbar?.show()
        return snackbar
    }

    override fun hiddenSnackbar() {
        snackbar?.let {
            if(it.isShown) {
                it.dismiss()
            }
        }
    }
    /** END TOAST SNAKCBAR MATERIAL */

    /** POPUP MATERIAL */
    override fun customPopupConfirm(
        type: String,
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

    override fun popupPrivacyAndPolicy(
        message: String?,
        actionX: String?,
        actionY: String?,
        listenerX: (() -> Unit)?,
        listenerY: (() -> Unit)?
    ): Dialog? {
        nDialog = ConfirmCustomImpl("").getShowPrivacy(this, message, actionX, actionY, listenerX, listenerY)
        nDialog?.show()
        return nDialog
    }

    override fun popupNoInternet(listener: (() -> Unit)?) : Dialog? {
        nDialog = ConfirmCustomImpl("").getShowNointernet(this, listener)
        nDialog?.show()
        return nDialog
    }

    override fun hiddenPopup() {
        if(nDialog != null && nDialog?.isShowing!!) {
            nDialog?.dismiss()
            nDialog = null
        }
    }
    /** END POPUP MATERIAL */

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
    fun hiddenSnackbar()
}

interface BasePopupImpl {
    fun customPopupConfirm(
        type: String,
        message: String?,
        actionX: String?,
        actionY: String?,
        listenerX: (() -> Unit)?,
        listenerY: (() -> Unit)?) : Dialog?
    fun popupPrivacyAndPolicy(
        message: String?,
        actionX: String?,
        actionY: String?,
        listenerX: (() -> Unit)?,
        listenerY: (() -> Unit)?
    ): Dialog?
    fun popupNoInternet(listener: (() -> Unit)?) : Dialog?
    fun hiddenPopup()
}