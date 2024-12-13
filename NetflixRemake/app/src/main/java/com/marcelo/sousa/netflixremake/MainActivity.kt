package com.marcelo.sousa.netflixremake

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.sousa.netflixremake.model.Category
import com.marcelo.sousa.netflixremake.model.Movie
import com.marcelo.sousa.netflixremake.util.Callback
import com.marcelo.sousa.netflixremake.util.CategoryTask

class MainActivity : AppCompatActivity(), Callback {
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_main)

        progressBar = findViewById(R.id.progressBar)

        val categories = mutableListOf<Category>()

        /* for (j in 0 until 5) {

            val movies = mutableListOf<Movie>()
            for (i in 0 until 5) {
                val movie = Movie(R.drawable.movie)
                val movie1 = Movie(R.drawable.movie_4)
                movies.add(movie)
                movies.add(movie1)
            }

            val category = Category("Category $j", movies)
            categories.add(category)
        }*/

        val adapter = CategoryAdapter(categories)
        val rvMain: RecyclerView = findViewById(R.id.rv_main)
        rvMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvMain.adapter = adapter

        CategoryTask(this).execute("https://api.tiagoaguiar.co/netflixapp/home?apiKey=6a9a03b3-7370-4bc2-af5e-afa9ea9d5b24")
    }

    override fun onPreExecute() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onResult(toCategories: List<Category>) {
        // aqui será quando o CategoryTask chamará de volta
        // (callback) - listener
        Log.i("Teste Activity", toCategories.toString())
        progressBar.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        progressBar.visibility = View.GONE
    }
}