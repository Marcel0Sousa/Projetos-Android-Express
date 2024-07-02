package com.marcelo.fitnesstracker

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var btnImc: LinearLayout
    private lateinit var rvMainActivity: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItens = mutableListOf<MainItem>()
        mainItens.add(
            MainItem(
                id = 1,
                drawableId = R.drawable.ic_imc,
                textString = R.string.label_imc,
                color = Color.GRAY
            )
        )

        mainItens.add(
            MainItem(
                id = 2,
                drawableId = R.drawable.sunny_20,
                textString = R.string.label_tmb,
                color = Color.YELLOW
            )
        )


        rvMainActivity = findViewById(R.id.rv_main)
        val adapter = MainAdapter(mainItens)
        rvMainActivity.adapter = adapter
        //rvMainActivity.layoutManager = LinearLayoutManager(this)
        rvMainActivity.layoutManager = GridLayoutManager(this, 2)

        /*btnImc = findViewById(R.id.btn_imc)
        btnImc.setOnClickListener {
            val intent = Intent(this, ImcActivity::class.java)
            startActivity(intent)
        }*/

    }
}