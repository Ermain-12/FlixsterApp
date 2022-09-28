package com.ermain.flixsterapp

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import okhttp3.Headers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import org.json.JSONException


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val NOW_PLAYING =
        "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
    private val imageURL = "https://image.tmdb.org/t/"

    private lateinit var rvMovies: RecyclerView
    private val movies = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvMovies = findViewById(R.id.rvMovies)

        val movieAdapter = MovieAdapter(this, movies)
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(this)

        val client = AsyncHttpClient()
        client.get(NOW_PLAYING, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure() $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                Log.i(TAG, "onSuccess() data: $json")
                try {
                    val movieJsonArray = json?.jsonObject?.getJSONArray("results")
                    if (movieJsonArray != null) {
                        movies.addAll(Movie.fromJsonArray(movieJsonArray))
                        movieAdapter.notifyDataSetChanged()
                    }
                    Log.i(TAG, "Movie List: $movies")
                } catch (e: JSONException) {
                    Log.e(TAG, "Encountered exception: $e")
                }
            }
        })
    }
}