package com.itbooh.fishapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Favourite(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: Int,
    @SerializedName("cat_id") var cat_id: String,
    @SerializedName("story_title") var story_title: String,
    @SerializedName("story_image") var story_image: String,
    @SerializedName("story_image_thumb") var story_image_thumb: String,
    @SerializedName("story_description") var story_description: String
)