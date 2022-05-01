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

            }
        })
        binding.rvHome.adapter = mAdapter
        mAdapter.setList(data)
    }

    private fun handleViews(isLoading : Boolean = false){
        binding.rvHome.isVisible = !isLoading

    }

}