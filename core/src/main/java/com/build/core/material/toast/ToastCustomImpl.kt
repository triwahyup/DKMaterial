package com.build.core.material.toast

import android.app.Activity
import android.widget.Toast
import com.build.core.R

class ToastCustomImpl(pType: String) : ToastCustom {
    private val type = pType

    override fun getToast(activity: Activity, message: String, duration: Int): Toast {
        when(type) {
            "success" -> {
                return ToastSuccess.getToast(activity, message, duration)
            }
            "error" -> {
                return ToastError.getToast(activity, message, duration)
            }
            "info" -> {
                return ToastInfo.getToast(activity, message, duration)
            }
            "warning" -> {
                return ToastWarning.getToast(activity, message, duration)
            }
        }
        return Toast.makeText(activity.applicationContext, message, duration)
    }
}

object ToastSuccess : ToastCustom {
    override val drawableId: Int
        get() = R.drawable.ic_xml_check_circle_white_24dp
    override val colorId: Int
        get() = R.color.green_500
}

object ToastError : ToastCustom {
    override val drawableId: Int
        get() = R.drawable.ic_xml_close_white_24dp
    override val colorId: Int
        get() = R.color.red_500
}

object ToastInfo : ToastCustom {
    override val drawableId: Int
        get() = R.drawable.ic_xml_info_white_24dp
    override val colorId: Int
        get() = R.color.blue_500
}

object ToastWarning : ToastCustom {
    override val drawableId: Int
        get() = R.drawable.ic_xml_warning_white_24dp
    override val colorId: Int
        get() = R.color.yellow_800
}