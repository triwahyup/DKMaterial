package com.build.core.material.dialog

import android.app.Activity
import android.app.Dialog
import com.build.core.R

class ConfirmCustomImpl(pType: String?) : ConfirmCustom {
    private val type = pType

    override fun getConfirm(
        activity: Activity,
        message: String?,
        actionX: String?,
        actionY: String?,
        listenerX: (() -> Unit)?,
        listenerY: (() -> Unit)?
    ): Dialog {
        when(type) {
            "confirm" -> {
                return ConfirmWarning.getConfirm(activity, message, actionX, actionY, listenerX, listenerY)
            }
            "upload_failure" -> {
                return UploadFailure.getConfirm(activity, message, actionX, actionY, listenerX, listenerY)
            }
        }
        return super.getConfirm(activity, message, actionX, actionY, listenerX, listenerY)
    }
}

object ConfirmWarning : ConfirmCustom {
    override val drawableBackground: Int
        get() = R.drawable.header_notif
    override val drawableShapeButton: Int
        get() = R.drawable.shape_rounded_success
    override val drawableIconShape: Int
        get() = R.drawable.ic_xml_check_green_100dp
}

object UploadFailure : ConfirmCustom {
    override val drawableBackground: Int
        get() = R.drawable.header_upload_failure
    override val drawableShapeButton: Int
        get() = R.drawable.shape_rounded_primary
    override val drawableIconShape: Int
        get() = R.drawable.ic_xml_cloud_upload_blue_100dp
}