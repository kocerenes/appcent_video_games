package com.example.appcentvideogames.model

import com.google.gson.annotations.SerializedName

data class GameDetail(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("metacritic")
    val metacritic: String?,
    @SerializedName("background_image")
    val imageUrl: String?,
    @SerializedName("is_fav")
    var isFav: Boolean = false

)