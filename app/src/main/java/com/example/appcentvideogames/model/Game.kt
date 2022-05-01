package com.example.appcentvideogames.model

import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("background_image")
    val imageUrl: String?,
    @SerializedName("is_fav")
    var isFav: Boolean = false
)