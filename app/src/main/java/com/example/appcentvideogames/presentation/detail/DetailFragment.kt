package com.example.appcentvideogames.presentation.detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.appcentvideogames.R
import com.example.appcentvideogames.base.BaseFragment
import com.example.appcentvideogames.databinding.FragmentDetailBinding
import com.example.appcentvideogames.model.GameDetail
import com.example.appcentvideogames.utils.Constants.API_KEY
import com.example.appcentvideogames.utils.loadImage
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding,DetailViewModel>(
    FragmentDetailBinding:: inflate
) {
    private val args by navArgs<DetailFragmentArgs>()
    override val viewModel by viewModels<DetailViewModel>()

    private lateinit var currentGame: GameDetail

    override fun onCreateFinished() {
        viewModel.getDetail(args.id,API_KEY)
    }

    override fun initializeListener() {

    }

    override fun observeEvents() {

        with(viewModel){
            detailResponse.observe(viewLifecycleOwner, Observer {
                it?.let {
                    handleView(false)
                    currentGame = it
                    val sharedPref = requireContext().getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE)
                    val isGameInFavorites = sharedPref.getBoolean(currentGame.name, false)
                    if (isGameInFavorites) {
                        binding.ivFavIcon.setImageResource(R.drawable.ic_favorite)
                        it.isFav = true
                    }
                    else {
                        binding.ivFavIcon.setImageResource(R.drawable.ic_favorite_border)
                        it.isFav = false
                    }
                    binding.ivGameImage.loadImage(it.imageUrl.toString())
                    binding.tvGameName.text = it.name
                    binding.tvGameReleased.text = it.released
                    binding.tvGameMetacritic.text = it.metacritic
                    binding.tvGameDescription.text = it.description
                    favClickButton(it)
                }
            })

            isLoading.observe(viewLifecycleOwner, Observer {
                handleView(it)
            })

            onError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun handleView(isLoading: Boolean = false){
        binding.detailGroup.isVisible = !isLoading
    }

    private fun favClickButton(gameDetail: GameDetail){
        binding.ivFavIcon.setOnClickListener{
            if (gameDetail.isFav){
                binding.ivFavIcon.setImageResource(R.drawable.ic_favorite_border)
                gameDetail.isFav = false

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.deleteFavoriteGameDB(requireContext(),gameDetail)
                    println("silindi")

                    val sharedPref = requireContext().getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE)
                    sharedPref.edit().remove(gameDetail.name).apply()
                }
            }
            else{
                binding.ivFavIcon.setImageResource(R.drawable.ic_favorite)
                gameDetail.isFav = true

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.addFavoriteGameDB(requireContext(),gameDetail)

                    val sharedPref = requireContext().getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE)
                    sharedPref.edit().putBoolean(gameDetail.name,true).apply()
                }
            }
        }
    }

}