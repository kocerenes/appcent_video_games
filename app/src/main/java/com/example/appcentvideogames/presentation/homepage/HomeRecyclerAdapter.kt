package com.example.appcentvideogames.presentation.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentvideogames.databinding.HomeRecyclerRowBinding
import com.example.appcentvideogames.model.Game

class HomeRecyclerAdapter(private val listener: ItemClickListener): RecyclerView.Adapter<HomeRecyclerAdapter.MViewHolder>(), Filterable {

    private var games = ArrayList<Game>()

    class MViewHolder(private val binding: HomeRecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: ItemClickListener, game: Game) {
            binding.onItemClickListener = listener
            binding.game = game
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HomeRecyclerRowBinding.inflate(layoutInflater, parent, false)
                return MViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MViewHolder.from(parent)

    override fun onBindViewHolder(holder: MViewHolder, position: Int) =
        holder.bind(listener, games[position])

    override fun getItemCount() = games.size

    fun setList(newList: List<Game>) {
        games.clear()
        games.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return gameFilter
    }

    private val gameFilter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = arrayListOf<Game>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(games)
            } else {
                val filterPattern = constraint.toString().lowercase().trim()
                for (item in games) {
                    if (item.name!!.lowercase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            val result = p1?.values as ArrayList<Game>
            listener.onFilteredNameOfGame(result.size)
            games.clear()
            games.addAll(result)
            notifyDataSetChanged()
        }
    }

}