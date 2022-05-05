package com.example.appcentvideogames.presentation.favoritepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentvideogames.R
import com.example.appcentvideogames.base.BaseFragment
import com.example.appcentvideogames.database.FavoriteGameDatabase
import com.example.appcentvideogames.databinding.FragmentFavoriteBinding
import com.example.appcentvideogames.model.Game
import com.example.appcentvideogames.model.GameDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.favorite_recycler_row.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(
    FragmentFavoriteBinding::inflate
) {
    override val viewModel by viewModels<FavoriteViewModel>()
    private lateinit var recyclerAdapter: FavoriteRecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateFinished() {

    }

    override fun initializeListener() {

    }

    override fun observeEvents() {
        with(viewModel) {
            favoriteGameResponse.observe(viewLifecycleOwner, Observer {
                it?.let {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.getDataFromDB(requireContext())
                        initRecycler()
                        withContext(Dispatchers.Main) {
                            //recyclerAdapter.setList(it)
                            //it?.let { it1 -> setRecycler(it1) }
                            setRecycler(it)

                        }
                    }
                }
            })
        }
    }

    private fun setRecycler(data: List<GameDetail?>) {
        recyclerAdapter = FavoriteRecyclerAdapter()
        binding.rvFavorite.adapter = recyclerAdapter
        recyclerAdapter.setList(data)
        println(data[0]?.name)
        println(data[0]?.metacritic)
    }

    private fun initRecycler() {
        recyclerView = binding.rvFavorite
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.visibility = View.VISIBLE
    }

}