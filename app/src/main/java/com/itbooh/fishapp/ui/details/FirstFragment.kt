package com.itbooh.fishapp.ui.details

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.itbooh.fishapp.R
import com.itbooh.fishapp.data.model.Fish
import com.itbooh.fishapp.sharedpref.AppPref
import com.itbooh.fishapp.utils.AdmobAdaptiveBannerAdController
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var firstViewModel : FirstViewModel
    private lateinit var adbanner : AdmobAdaptiveBannerAdController
    private lateinit var fishdis: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstViewModel =
            ViewModelProviders.of(this).get(FirstViewModel::class.java)
        adbanner = AdmobAdaptiveBannerAdController()
        activity?.let { adbanner.initializeAdBanner(it,R.id.adViewContainer,R.string.banner_details,view,true) }
        (activity as? AppCompatActivity)?.supportActionBar?.title = activity?.intent?.getStringExtra("fishName").toString()
        view.findViewById<TextView>(R.id.textview_first)?.text =
            activity?.intent?.getIntExtra("fishId",0).toString()
       val fishD : MutableList<Fish> =  firstViewModel.getFishDetails(activity?.intent?.getIntExtra("fishId",0))
        view.findViewById<TextView>(R.id.tvTitle)?.text = fishD[0].story_title
         val img :ImageView = view.findViewById(R.id.ivImage)
        Picasso.get()
            .load(fishD[0].story_image)
            .placeholder(R.drawable.fish_pa)
            .into(img, object : Callback {
                override fun onSuccess() {
                    Log.d("f", "success")
                }

                override fun onError(e: Exception?) {
                    Log.d("f", "error")
                }
            })
          val mWebView =   view.findViewById<WebView>(R.id.webView_details)
        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        val text: String
        if (AppPref.isLightThemeEnabled) {
            text =
                "<style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/ptsansNarrow-regular.ttf\")}body,* {font-family: MyFont; color:#666666; font-size: 13px;line-height:1.2}img{max-width:100%;height:auto; border-radius: 3px;}</style>";
        }else{
            text =
                "<style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/ptsansNarrow-regular.ttf\")}body,* {font-family: MyFont; background-color:#1A1919;color:#f6f6f6; font-size: 13px;line-height:1.2}img{max-width:100%;height:auto; border-radius: 3px;}</style>";
        }
        mWebView.loadDataWithBaseURL("",  text+"<div>" + fishD[0].story_description + "</div>", "text/html", "utf-8", null)
        val textFromHtml: Spanned? = Html.fromHtml(Html.fromHtml(fishD[0].story_description).toString())
        fishdis = textFromHtml.toString()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mShare -> {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    fishdis+"\n Fish Book App"
                )
                sendIntent.type = "text/plain"
                startActivity(sendIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
