package com.itbooh.fishapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Category (
    @PrimaryKey(autoGenerate = true)
    @SerializedName("cid") var id: Int,
    @SerializedName("category_name") var category_name: String,
    @SerializedName("category_image") var category_image: String,
    @SerializedName("category_image_thumb") var category_image_thumb: String
)