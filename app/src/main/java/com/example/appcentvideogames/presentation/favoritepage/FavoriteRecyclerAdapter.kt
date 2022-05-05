package com.example.appcentvideogames.presentation.favoritepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentvideogames.databinding.FavoriteRecyclerRowBinding
import com.example.appcentvideogames.model.Game
import com.example.appcentvideogames.model.GameDetail
import com.example.appcentvideogames.utils.loadImage
import kotlinx.android.synthetic.main.favorite_recycler_row.view.*
import kotlinx.android.synthetic.main.fragment_detail.view.*

class FavoriteRecyclerAdapter() :
    RecyclerView.Adapter<FavoriteRecyclerAdapter.FavoriteViewHolder>() {

    private var games = ArrayList<GameDetail?>()

    class FavoriteViewHolder(private val binding: FavoriteRecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gameDetail:GameDetail){
            binding.game = gameDetail
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): FavoriteViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteRecyclerRowBinding.inflate(layoutInflater,parent,false)
                return FavoriteViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavoriteViewHolder.from(parent)

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
    }

    override fun getItemCount() = games.size

    fun setList(newList: List<GameDetail?>) {
        games.clear()
        games.addAll(newList)
        notifyDataSetChanged()
    }

}