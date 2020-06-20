package com.itbooh.fishapp.ui.privacy

import android.os.Bundle
import android.webkit.WebView
import com.itbooh.fishapp.R
import com.itbooh.fishapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_privacy.*

class PrivacyActivity : BaseActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)
        setSupportActionBar(toolbar)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        val mWebView = findViewById<WebView>(R.id.webView_details)
        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
if(intent?.getStringExtra("act").toString().equals("privacy")){
    actionbar!!.title = "Privacy Policy"
    mWebView.loadUrl("https://fish.itbooh.xyz/privacy_policy.html")
}else{
    actionbar!!.title = "Terms & Conditions"
    mWebView.loadUrl("https://fish.itbooh.xyz/terms_conditions.html")
}
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
