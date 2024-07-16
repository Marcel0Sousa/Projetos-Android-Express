package com.marcelo.fitnesstracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.fitnesstracker.Adapters.ListCalcAdapter
import com.marcelo.fitnesstracker.model.Calc

class ListCalcActivity : AppCompatActivity() {
    private lateinit var rvListCalc: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_calc)

        val type =
            intent?.extras?.getString("type") ?: throw IllegalStateException("Type not found")

        Thread {
            val app = application as App
            val calcDao = app.db.calcDao()
            val registerByType = calcDao.getRegisterByType(type)

            runOnUiThread {
                Log.i("Type", "registerByType: $registerByType\n")

                val listItem = mutableListOf<Calc>()
                for (item in registerByType) {
                    listItem.add(item)
                }

                rvListCalc = findViewById(R.id.rv_list_calc)
                val adapter = ListCalcAdapter(listItem)
                rvListCalc.adapter = adapter
                rvListCalc.layoutManager = LinearLayoutManager(this)
            }
        }.start()
    }
}