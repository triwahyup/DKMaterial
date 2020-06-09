package com.build.core.activity.page

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import com.build.core.BaseActivity
import com.build.core.R

class NoConnection : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_no_internet)
        retry()
    }

    private fun retry() {
        val layoutProgressBar = findViewById<LinearLayout>(R.id.layoutProgressBar)
        val layoutNoInternet = findViewById<LinearLayout>(R.id.layoutNoInternet)
        val bRetry = findViewById<Button>(R.id.retry)

        layoutProgressBar?.visibility = View.GONE;
        layoutNoInternet?.visibility = View.VISIBLE

        bRetry.setOnClickListener(View.OnClickListener {
            layoutProgressBar?.visibility = View.VISIBLE
            layoutNoInternet?.visibility = View.GONE
            Handler().postDelayed(Runnable {
                layoutProgressBar?.visibility = View.GONE
                layoutNoInternet?.visibility = View.VISIBLE
            }, 1000)
        })
    }
}