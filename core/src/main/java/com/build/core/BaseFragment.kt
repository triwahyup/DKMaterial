package com.build.core

import android.app.Dialog
import android.view.View
import androidx.fragment.app.Fragment
import com.build.core.material.dialog.ConfirmCustomImpl
import com.build.core.material.snackbar.SnackbarCustomImpl
import com.build.core.material.toast.ToastCustomImpl
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment(),
    BaseToastImpl, BaseSnackbarImpl, BasePopupImpl {

    private var snackbar: Snackbar? = null
    private var nDialog: Dialog? = null
    lateinit var nView: View

    /** TOAST SNACKBAR MATERIAL */
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
            snackbar = SnackbarCustomImpl(type).getSnackbar(it, message, duration, label, listener)
            snackbar?.show()
            return snackbar
        }
        return null
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
        activity?.let {
            nDialog = ConfirmCustomImpl(type).getConfirm(it, message, actionX, actionY, listenerX, listenerY)
            nDialog?.show()
            return nDialog
        }
        return null
    }

    override fun popupPrivacyAndPolicy(
        message: String?,
        actionX: String?,
        actionY: String?,
        listenerX: (() -> Unit)?,
        listenerY: (() -> Unit)?
    ) : Dialog? {
        activity?.let {
            nDialog = ConfirmCustomImpl("").getShowPrivacy(it, message, actionX, actionY, listenerX, listenerY)
            nDialog?.show()
            return nDialog
        }
        return null
    }

    override fun popupNoInternet(listener: (() -> Unit)?) : Dialog? {
        activity?.let {
            nDialog = ConfirmCustomImpl("").getShowNointernet(it, listener)
            nDialog?.show()
            return nDialog
        }
        return null
    }

    override fun hiddenPopup() {
        if(nDialog != null && nDialog?.isShowing!!) {
            nDialog?.dismiss()
            nDialog = null
        }
    }
    /** END POPUP MATERIAL */
}