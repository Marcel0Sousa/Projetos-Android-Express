package com.marcelo.sousa.netflixremake

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.sousa.netflixremake.model.Category
import com.marcelo.sousa.netflixremake.model.Movie

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_main)

        val categories = mutableListOf<Category>()
        for (j in 0 until 5) {

            val movies = mutableListOf<Movie>()
            for (i in 0 until 5) {
                val movie = Movie(R.drawable.movie)
                val movie1 = Movie(R.drawable.movie_4)
                movies.add(movie)
                movies.add(movie1)
            }

            val category = Category("Category $j", movies)
            categories.add(category)
        }


        val adapter = CategoryAdapter(categories)
        val rvMain: RecyclerView = findViewById(R.id.rv_main)
        rvMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvMain.adapter = adapter
    }
}