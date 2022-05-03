package com.example.appcentvideogames.presentation.homepage

import com.example.appcentvideogames.model.Game

interface ItemClickListener {

    fun onItemClick(game: Game)
    fun onFilteredNameOfGame(nameLengt: Int)
}