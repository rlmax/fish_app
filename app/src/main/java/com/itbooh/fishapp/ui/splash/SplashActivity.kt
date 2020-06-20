package com.itbooh.fishapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.itbooh.fishapp.R
import com.itbooh.fishapp.ui.base.BaseActivity
import com.itbooh.fishapp.ui.main.MainActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        },2000)
    }

}
