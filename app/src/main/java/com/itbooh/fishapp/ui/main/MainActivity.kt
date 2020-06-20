package com.itbooh.fishapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.itbooh.fishapp.App
import com.itbooh.fishapp.R
import com.itbooh.fishapp.data.db.AppDatabase
import com.itbooh.fishapp.data.network.ApiService
import com.itbooh.fishapp.ui.base.BaseActivity
import com.itbooh.fishapp.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    var apiService: ApiService = App.api!!
    var appDatabase: AppDatabase = AppDatabase.getInstance()
    private lateinit var adView: AdView
    private var initialLayoutComplete = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home,R.id.nav_marine_fish,R.id.nav_fresh_water,R.id.nav_amphidromous,R.id.nav_subtropical_fish,R.id.nav_aquatic_plants), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Initialize the Mobile Ads SDK.
//        MobileAds.initialize(this) { }
//        setupBannerAd()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
              //  Toast.makeText(this,"Setting",Toast.LENGTH_LONG).show()
                val intent = Intent (this@MainActivity, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

//    private fun setupBannerAd() {
//        adView = AdView(this)
//        adViewContainer.addView(adView)
//        adViewContainer.viewTreeObserver.addOnGlobalLayoutListener {
//            if (!initialLayoutComplete) {
//                initialLayoutComplete = true
//
//                adView.adUnitId = getString(R.string.banner_id_home)
//                adView.adSize = getAdaptiveBannerAdSize(adViewContainer)
//                adView.loadAd(AdRequest.Builder().build())
//            }
//        }
//    }
//
//    /**
//     * Returns the size of the Adaptive Banner Ad based on the screen width
//     */
//    private fun getAdaptiveBannerAdSize(adViewContainer: FrameLayout): AdSize {
//        val display = windowManager.defaultDisplay
//        val outMetrics = DisplayMetrics()
//        display.getMetrics(outMetrics)
//
//        val density = outMetrics.density
//
//        var adWidthPixels = adViewContainer.width.toFloat()
//        if (adWidthPixels == 0f) {
//            adWidthPixels = outMetrics.widthPixels.toFloat()
//        }
//
//        val adWidth = (adWidthPixels / density).toInt()
//        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
//    }
}
