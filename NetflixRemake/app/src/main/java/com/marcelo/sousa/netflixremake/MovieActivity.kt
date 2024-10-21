package com.marcelo.sousa.netflixremake

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val toolbar: Toolbar = findViewById(R.id.movie_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow)
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