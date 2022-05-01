package com.example.appcentvideogames.presentation.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentvideogames.databinding.HomeRecyclerRowBinding
import com.example.appcentvideogames.model.Game

class HomeRecyclerAdapter(private val listener: ItemClickListener): RecyclerView.Adapter<HomeRecyclerAdapter.MViewHolder>() {

    private var games = emptyList<Game>()

    class MViewHolder(private val binding: HomeRecyclerRowBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(listener: ItemClickListener,game: Game){
            binding.onItemClickListener = listener
            binding.game = game
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): MViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HomeRecyclerRowBinding.inflate(layoutInflater,parent,false)
                return MViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MViewHolder.from(parent)

    override fun onBindViewHolder(holder: MViewHolder, position: Int) = holder.bind(listener,games[position])

    override fun getItemCount() = games.size

    fun setList(newList: List<Game>){
        games = newList
        notifyDataSetChanged()
    }

}