package com.ermain.flixsterapp

import com.google.gson.annotations.SerializedName
import org.json.JSONArray

data class Movie(

    @SerializedName(value = "movie_id")
    val movieId: Int,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String
) {
    val posterImageURL = "https://image.tmdb.org/t/w342$posterPath"
    companion object {

        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJsonArray.length()) {
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getString("original_title"),
                        movieJson.getString("overview"),
                        movieJson.getString("poster_path")
                    )
                )
            }

            return movies
        }
    }
}
