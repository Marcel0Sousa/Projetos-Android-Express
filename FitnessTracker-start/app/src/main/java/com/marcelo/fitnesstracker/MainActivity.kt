package com.marcelo.fitnesstracker

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.marcelo.fitnesstracker.Adapters.MainAdapter
import com.marcelo.fitnesstracker.model.MainItens

class MainActivity : AppCompatActivity() {
    private lateinit var btnImc: LinearLayout
    private lateinit var rvMainActivity: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItens = mainItens()

        rvMainActivity = findViewById(R.id.rv_main)
        val adapter = MainAdapter(mainItens) { id ->
            when (id) {
                1 -> {
                    val intent = Intent(
                        this@MainActivity,
                        ImcActivity::class.java
                    )
                    startActivity(intent)
                }

                2 -> {
                    val intent = Intent(
                        this@MainActivity,
                        TmbActivity::class.java
                    )
                    startActivity(intent)
                }

                3-> {
                    Snackbar.make(rvMainActivity, "Funcionalidade em desenvolvimento", Snackbar.LENGTH_LONG).show()
                }

                4-> {
                    Snackbar.make(rvMainActivity, "Funcionalidade em desenvolvimento", Snackbar.LENGTH_LONG).show()
                }

            }

        }
        rvMainActivity.adapter = adapter
        //rvMainActivity.layoutManager = LinearLayoutManager(this)
        rvMainActivity.layoutManager = GridLayoutManager(this, 2)

    }

    private fun mainItens(): MutableList<MainItens> {
        val mainItens = mutableListOf<MainItens>()
        mainItens.add(
            MainItens(
                id = 1,
                drawableId = R.drawable.ic_imc,
                textString = R.string.label_imc,
                color = R.color.pewter_blue
            )
        )

        mainItens.add(
            MainItens(
                id = 2,
                drawableId = R.drawable.ic_tmb,
                textString = R.string.label_tmb,
                color = R.color.mauvelous
            )
        )

        mainItens.add(
            MainItens(
                id = 3,
                drawableId = R.drawable.sunny_24,
                textString = R.string.icg,
                color = R.color.olivine
            )
        )

        mainItens.add(
            MainItens(
                id = 4,
                drawableId = R.drawable.ic_fcm,
                textString = R.string.fcm,
                color = R.color.dark_sky_blue
            )
        )

        return mainItens
    }

}