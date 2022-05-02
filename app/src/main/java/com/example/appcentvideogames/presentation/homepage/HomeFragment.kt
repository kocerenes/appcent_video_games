package com.example.appcentvideogames.presentation.homepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.appcentvideogames.R
import com.example.appcentvideogames.base.BaseFragment
import com.example.appcentvideogames.databinding.FragmentHomeBinding
import com.example.appcentvideogames.model.Game
import com.example.appcentvideogames.model.GameResponse
import com.example.appcentvideogames.utils.Constants.API_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // hilt için
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    //view model'ı tanımla
    override val viewModel by viewModels<HomeViewModel>()
    //ilk 3 oyun
   // private val topThreeGame = arrayListOf<Game>()

    override fun onCreateFinished() {
        Log.e("dene","oncreat")
        viewModel.getData(API_KEY)

    }

    //tıklama listenerlarını yazacağımız fonksiyon
    override fun initializeListener() {

    }

    //livedataları gözlemleyeceğimiz fonksiyon
    override fun observeEvents() {

        with(viewModel){


            gameResponse.observe(viewLifecycleOwner, Observer {

                it?.let {
                    //it.results?.let { it2 -> addTopThreeGame(it2) }
                    it.results?.let { it1 -> setRecycler(it1) }
                }
            })

            onError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
            })
        }

    }

    private fun setRecycler(data:List<Game>){
        val mAdapter = HomeRecyclerAdapter(object : ItemClickListener{
            override fun onItemClick(game: Game) {
                if (game.id != null){
                    val navigation = HomeFragmentDirections.actionHomeFragmentToDetailFragment(game.id)
                    Navigation.findNavController(requireView()).navigate(navigation)
                }
            }
        })
        binding.rvHome.adapter = mAdapter
        mAdapter.setList(data)
    }

    private fun handleViews(isLoading : Boolean = false){
        binding.rvHome.isVisible = !isLoading
    }

    //ilk 3 oyunu al
    /*private fun addTopThreeGame(data:List<Game>){
        topThreeGame.add(data[0])
        topThreeGame.add(data[1])
        topThreeGame.add(data[2])
    }*/

}