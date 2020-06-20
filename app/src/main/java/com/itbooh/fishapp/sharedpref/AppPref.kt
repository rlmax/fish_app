package com.itbooh.fishapp.sharedpref

import com.chibatching.kotpref.KotprefModel

object AppPref : KotprefModel() {
    var isLightThemeEnabled by booleanPref(true)
}