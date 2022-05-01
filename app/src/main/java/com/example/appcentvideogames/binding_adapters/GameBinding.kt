package com.example.appcentvideogames.binding_adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.appcentvideogames.utils.loadImage

class GameBinding {

    companion object{
        //https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg

        @BindingAdapter("load_image")
        @JvmStatic
        fun loadImage(imageView: ImageView,gameImageCode: String){
            val imageUrl = "$gameImageCode"
            imageView.loadImage(imageUrl)
        }

    }

}