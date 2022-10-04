package com.ermain.flixsterapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.json.JSONArray

@Parcelize
data class Movie(

    @SerializedName(value = "movie_id")
    val movieId: Int,

    val voteAverage: Double,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String
): Parcelable {
    @IgnoredOnParcel
    val posterImageURL = "https://image.tmdb.org/t/p/w500/$posterPath"
    companion object {

        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJsonArray.length()) {
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getDouble("vote_average"),
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
