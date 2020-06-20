package com.itbooh.fishapp.utils
import android.app.Activity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout

open class AdmobAdaptiveBannerAdController {

    private lateinit var mAdView: AdView

    fun initializeAdBanner(
        activity: Activity, adContainerViewId: Int, adKeyId: Int,
        root: View?, fragemnt: Boolean) {

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(activity) { }
        val adContainerView : FrameLayout
        if(fragemnt){
            adContainerView = root!!.findViewById<FrameLayout>(adContainerViewId)

        }else{
           adContainerView = activity.findViewById<FrameLayout>(adContainerViewId)

        }

        // Step 1 - Create an AdView and set the ad unit ID on it.
        mAdView = AdView(activity)
        mAdView.adUnitId = activity.getString(adKeyId)

        // Add adView to its container and load ads
        adContainerView.addView(mAdView)
        loadBanner(activity)

    }

    private fun loadBanner(activity: Activity) {

        // Create an ad request. Check your logcat output for the hashed device ID
        // to get test ads on a physical device, e.g.,
        // "Use AdRequest.Builder.addTestDevice("ABCDE0123") to get test ads on this
        // device."
        val adRequest = AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build()

        val adSize = getAdSize(activity)

        // Step 4 - Set the adaptive ad size on the ad view.
        mAdView.adSize = adSize

        // Step 5 - Start loading the ad in the background.
        mAdView.loadAd(adRequest)

    }

    private fun getAdSize(activity: Activity) : AdSize {

        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        val display = activity.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val widthPixels = outMetrics.widthPixels.toFloat()
        val density = outMetrics.density

        val adWidth = (widthPixels / density).toInt()

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)

    }
     fun onPause() {
         mAdView.pause()

    }

     fun onResume() {
         mAdView.resume()

    }

     fun onDestroy() {
         mAdView.destroy()

    }
}