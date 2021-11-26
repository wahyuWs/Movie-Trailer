package com.kelompok7.rpl.movietrailer.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kelompok7.rpl.movietrailer.databinding.ItemSearchBinding
import com.kelompok7.rpl.movietrailer.model.Movies
import com.kelompok7.rpl.movietrailer.ui.halamandetail.DetailActivity

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(), Filterable {

    val dataMovie = ArrayList<Movies>()
    private val dataMovieFiltered = ArrayList<Movies>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemSearchBinding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(itemSearchBinding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(dataMovieFiltered[position])
    }

    override fun getItemCount(): Int = dataMovieFiltered.size

    class SearchViewHolder(private val binding: ItemSearchBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movies) {
            Glide.with(itemView.context)
                .load(data.movieImage)
                .transform(RoundedCorners(18))
                .into(binding.imagePoster)

            binding.titleMovie.text = data.movieTitle
            binding.kategoriFilm.text = data.movieGenre
            binding.duration.text = data.movieDuration

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val filtered = ArrayList<Movies>()

                for (movie in dataMovie) {
                    if (movie.toString().toLowerCase().contains(p0.toString().toLowerCase())) {
                        filtered.add(movie)
                    }
                }

                val filterResult = FilterResults()
                filterResult.values = filtered
                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                dataMovieFiltered.clear()
                dataMovieFiltered.addAll(p1?.values as Collection<Movies>)
                notifyDataSetChanged()
            }
        }
    }
}