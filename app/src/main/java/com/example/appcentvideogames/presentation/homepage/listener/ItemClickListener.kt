package com.example.appcentvideogames.presentation.homepage.listener

import com.example.appcentvideogames.model.Game

interface ItemClickListener {

    fun onItemClick(game: Game)
    fun onFilteredNameOfGame(nameLength: Int)
}