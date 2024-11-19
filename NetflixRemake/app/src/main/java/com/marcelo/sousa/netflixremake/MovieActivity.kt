package com.marcelo.sousa.netflixremake

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.sousa.netflixremake.model.Movie

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val tvTitle: TextView = findViewById(R.id.movie_txt_title)
        val tvDescription: TextView = findViewById(R.id.movie_txt_title)
        val tvCast: TextView = findViewById(R.id.movie_txt_cast)
        val recyclerView: RecyclerView = findViewById(R.id.movie_rv_similar)

        tvTitle.text = "Batman - O Cavaleiro das Trevas"
        tvDescription.text = "Essa é a descricão do filme Batman"
        tvCast.text = getString(R.string.cast, "Ator A, Ator B, Ator C")

        val movies = mutableListOf<Movie>()
        /*for (i in 0 until 15) {
            val movie = Movie(R.drawable.movie)
            movies.add(movie)
        }*/

        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = MovieAdapter(movies, R.layout.movie_item_similar)

        val toolbar: Toolbar = findViewById(R.id.movie_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // busca o desenhavel (layer-list)
        val layerDrawable: LayerDrawable = ContextCompat.getDrawable(this, R.drawable.shadows) as LayerDrawable

        // busca a capa do filme
        val movieCover = ContextCompat.getDrawable(this, R.drawable.movie_4)

        // atribui a nova capa ao layer-list
        layerDrawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)

        // set a capa no ImageView
        val imageCover: ImageView = findViewById(R.id.movie_img)
        imageCover.setImageDrawable(layerDrawable)
    }
}