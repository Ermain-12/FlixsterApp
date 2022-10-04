package com.ermain.flixsterapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val MOVIE_EXTRA = "MOVIE_EXTRA"
private const val TAG = "MovieAdapter"
class MovieAdapter(private val context: Context, private val movies: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val tvTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.textViewOverView)
        private val ivPoster = itemView.findViewById<ImageView>(R.id.imageView)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie): Unit {
            tvTitle.text = movie.originalTitle
            tvOverview.text = movie.overview
            Glide.with(context).load(movie.posterImageURL).into(ivPoster)
        }

        override fun onClick(v: View?) {
            // Get a notification of which view was clicked on
            val movie = movies[adapterPosition]
            Toast.makeText(context, movie.originalTitle, Toast.LENGTH_SHORT).show()
            // Then, navigate to said activity
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
