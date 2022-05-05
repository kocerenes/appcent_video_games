package com.example.appcentvideogames.presentation.favoritepage

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appcentvideogames.database.FavoriteGameDatabase
import com.example.appcentvideogames.model.GameDetail

class FavoriteViewModel : ViewModel() {

    val favoriteGameResponse: MutableLiveData<List<GameDetail?>> = MutableLiveData()

    fun getDataFromDB(context: Context) {
        val dao = FavoriteGameDatabase(context).favoriteGameDAO()
        val favoriteGames = dao.getAllGamesDetail()
        showFavoriteGame(favoriteGames)
    }

    private fun showFavoriteGame(favoriteGameList: List<GameDetail>) {
        favoriteGameResponse.value = favoriteGameList
    }

}