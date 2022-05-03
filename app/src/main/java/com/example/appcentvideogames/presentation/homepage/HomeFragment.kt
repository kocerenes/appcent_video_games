package com.example.appcentvideogames.presentation.homepage

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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
    //private lateinit var searchView: SearchView

    private lateinit var recyclerAdapter: HomeRecyclerAdapter
    //private val games = arrayListOf<Game>()


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
                    it.results?.let { it2 -> setSearchView(it2) }
                }
            })

            onError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun setRecycler(data:List<Game>){
        recyclerAdapter = HomeRecyclerAdapter(object : ItemClickListener{
            override fun onItemClick(game: Game) {
                if (game.id != null){
                    val navigation = HomeFragmentDirections.actionHomeFragmentToDetailFragment(game.id)
                    Navigation.findNavController(requireView()).navigate(navigation)
                }
            }

            override fun onFilteredNameOfGame(nameLength: Int) {
                if (nameLength == 0) {
                    binding.constraintLayout.visibility = View.VISIBLE
                }
                else {
                    binding.constraintLayout.visibility = View.GONE
                }
            }
        })
        binding.rvHome.adapter = recyclerAdapter
        recyclerAdapter.setList(data)
    }

    private fun setSearchView(games: List<Game>) {
        binding.svHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText?.length!! >= 3) {
                    binding.vp2Home.visibility = View.GONE
                    recyclerAdapter.setList(games)
                    recyclerAdapter.filter.filter(newText)
                }
                else if (newText.isEmpty()) {
                    binding.vp2Home.visibility = View.VISIBLE
                    recyclerAdapter.setList(games)
                }
                return true
            }
        })
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