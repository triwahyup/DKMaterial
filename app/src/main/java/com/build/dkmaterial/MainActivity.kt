package com.build.dkmaterial

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.build.core.BaseActivity
import com.build.core.activity.page.NoConnection
import com.build.dkmaterial.R
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        init_toast_snackbar()
        init_popup()
        init_animation()

        page_no_internet.setOnClickListener {
            startActivity(Intent(this, NoConnection::class.java))
        }
    }

    fun init_toast_snackbar() {
        snackbar_success.setOnClickListener {
            showSnackbar("success", "Berhasil menyimpan data ...")
        }

        toast_success.setOnClickListener {
            showToast("success", "Success" +"\t" +
                    ""+"\t")
        }
    }

    fun init_popup() {
        popup_nointernet.setOnClickListener {
            popupNoInternetAccess {
                showSnackbar("warning", "Snackbar Warning")
            }
        }
        popup_gdpr.setOnClickListener {
            popupPrivacyAndPolicy(
                getString(R.string.dummy_privacy),
                "Disagree",
                "Agree",
                { showSnackbar("error", "Snackbar Error")},
                { showSnackbar("success", "Snackbar Success")}
            )
        }
        popup_upload.setOnClickListener {
            customPopupConfirm(
                "upload_failure",
                "Gagal upload file. Silakan cek koneksi internet anda!",
                "Cancel",
                "Retry",
                { showSnackbar("inbox", "Snackbar Inbox")},
                { showSnackbar("info", "Snackbar Info")}
            )
        }
        popup_custom.setOnClickListener {
            customPopupConfirm(
                "confirm",
                "Apakah anda yakin ingin menghapus data ini ?",
                "Ya",
                "Batal",
                { showSnackbar("inbox", "Snackbar Inbox")},
                { showSnackbar("info", "Snackbar Info")}
            )
        }
    }

    fun init_animation() {
        anim_rotate.setOnClickListener {
            anim_rotate.visibility = View.VISIBLE

            val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            anim_rotate.startAnimation(animation)
        }
        anim_bounce.setOnClickListener {
            anim_bounce.visibility = View.VISIBLE

            val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
            anim_bounce.startAnimation(animation)
        }
        anim_slide_up.setOnClickListener {
            anim_slide_up.visibility = View.VISIBLE

            val animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
            anim_slide_up.startAnimation(animation)
        }
        anim_slide_down.setOnClickListener {
            anim_slide_down.visibility = View.VISIBLE

            val animation = AnimationUtils.loadAnimation(this, R.anim.slide_down)
            anim_slide_down.startAnimation(animation)
        }
        anim_fade_in.setOnClickListener {
            anim_fade_in.visibility = View.VISIBLE

            val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            anim_fade_in.startAnimation(animation)
        }
        anim_fade_out.setOnClickListener {
            anim_fade_out.visibility = View.VISIBLE

            val animation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            anim_fade_out.startAnimation(animation)
        }
    }
}