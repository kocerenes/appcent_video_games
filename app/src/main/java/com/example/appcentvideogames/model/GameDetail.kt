package com.example.appcentvideogames.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GameDetail(
    @PrimaryKey
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
    @ColumnInfo(name= "background_image")
    @SerializedName("background_image")
    val imageUrl: String?,
    @ColumnInfo(name = "is_fav")
    @SerializedName("is_fav")
    var isFav: Boolean = false

)