package com.itbooh.fishapp.data.model

import com.google.gson.annotations.SerializedName

data class FishDto(
    @SerializedName("latest_stories")
    val results: MutableList<Fish>,
    @SerializedName("categories")
    val resultCategory: MutableList<Category>
)
