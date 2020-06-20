package com.itbooh.fishapp.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.itbooh.fishapp.R
import com.itbooh.fishapp.sharedpref.AppPref
import com.itbooh.fishapp.ui.about.AboutUsActivity
import com.itbooh.fishapp.ui.base.BaseActivity
import com.itbooh.fishapp.ui.privacy.PrivacyActivity
import com.itbooh.fishapp.utils.AdmobAdaptiveBannerAdController
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity() {

    private lateinit var adbanner : AdmobAdaptiveBannerAdController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Settings"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        val swTheme : SwitchCompat = findViewById(R.id.swTheme)
        swTheme.isChecked = !AppPref.isLightThemeEnabled
        swTheme.setOnCheckedChangeListener { compoundButton, isChecked ->
            // Change theme after dismiss to prevent memory leak
                if (isChecked) setThemeMode(AppCompatDelegate.MODE_NIGHT_YES) else setThemeMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        val rlAbout :RelativeLayout = findViewById(R.id.rlAbout)
        rlAbout.setOnClickListener {
            //Your code here
            val intent = Intent (this@SettingsActivity, AboutUsActivity::class.java)
            startActivity(intent)
        }
        val rlPrivacy :RelativeLayout = findViewById(R.id.rlPrivacy)
        rlPrivacy.setOnClickListener {
            //Your code here
            val intent = Intent (this@SettingsActivity, PrivacyActivity::class.java)
            intent.putExtra("act","privacy")
            startActivity(intent)
        }
        val rlTermsConditions :RelativeLayout = findViewById(R.id.rlTermsConditions)
        rlTermsConditions.setOnClickListener {
            //Your code here
            val intent = Intent (this@SettingsActivity, PrivacyActivity::class.java)
            intent.putExtra("act","terms")
            startActivity(intent)
        }

        adbanner = AdmobAdaptiveBannerAdController()
       adbanner.initializeAdBanner(this,R.id.adViewContainer,R.string.banner_details,null,false)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setThemeMode(themeMode: Int) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
        AppPref.isLightThemeEnabled = themeMode == AppCompatDelegate.MODE_NIGHT_NO
    }

}
