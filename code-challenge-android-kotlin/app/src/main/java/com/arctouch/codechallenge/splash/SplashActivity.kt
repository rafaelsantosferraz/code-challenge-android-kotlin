package com.arctouch.codechallenge.splash

import android.content.Intent
import android.os.Bundle
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.app.MainActivity
import com.arctouch.codechallenge.base.presentation.ui.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
