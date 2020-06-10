package com.build.core.activity.page

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.build.core.BaseActivity
import com.build.core.R
import com.build.core.utils.gone
import com.build.core.utils.visible

class ErrInternalServer : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_err_internal_server)
        eventOnClick()
    }

    private fun eventOnClick() {
        val layoutProgressBar = findViewById<LinearLayout>(R.id.layoutProgressBar)
        val layoutErrorDesc = findViewById<LinearLayout>(R.id.layoutErrorDesc)
        val bRetry = findViewById<Button>(R.id.retry)

        layoutProgressBar.gone()
        layoutErrorDesc.visible()

        bRetry.setOnClickListener(View.OnClickListener {
            layoutProgressBar.visible()
            layoutErrorDesc.gone()
            Handler().postDelayed(Runnable {
                layoutProgressBar.gone()
                layoutErrorDesc.visible()
            }, 1000)
        })

        val bBack = findViewById<Button>(R.id.back)
        bBack.setOnClickListener {
            finish()
        }
    }
}