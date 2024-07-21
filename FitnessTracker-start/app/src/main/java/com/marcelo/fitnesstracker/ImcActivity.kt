package com.marcelo.fitnesstracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.marcelo.fitnesstracker.model.Calc


class ImcActivity : AppCompatActivity() {
    private lateinit var edtWeight: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        edtWeight = findViewById(R.id.edt_imc_weight)
        edtHeight = findViewById(R.id.edt_imc_height)
        btnSend = findViewById(R.id.btn_imc_send)

        btnSend.setOnClickListener {
            if (!validate()) {
                Toast.makeText(this, R.string.fields_messages, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val weight = edtWeight.text.toString().toInt()
            val height = edtHeight.text.toString().toInt()

            val result = calculateImc(weight, height)

            Log.i("imc", calculateImc(weight, height).toString())
            val imcResponseId = imcResponse(result)

            AlertDialog.Builder(this)
                .setTitle(getString(R.string.imc_response, calculateImc(weight, height)))
                .setMessage(imcResponseId)
                .setPositiveButton(
                    android.R.string.ok
                ) { dialog, which ->

                }
                .setNegativeButton(R.string.save) {dialog, whitch ->
                    Thread {
                        val app = application as App
                        val calcDao = app.db.calcDao()
                        calcDao.insert(Calc(type = "imc", response = result))

                        runOnUiThread {
                            /*val view: LinearLayout = findViewById(R.id.imc_layout)
                            Snackbar.make(view, R.string.calc_saved, Snackbar.LENGTH_LONG).show()*/
                            finish()
                            onOpenActivity()

                        }
                    }.start()
                }
                .create()
                .show()

            // hide keyboard
            val service = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            service.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {
                finish()
                onOpenActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onOpenActivity() {
        val intent = Intent(this@ImcActivity, ListCalcActivity::class.java)
        intent.putExtra("type", "imc")
        startActivity(intent)
    }

    @StringRes
    private fun imcResponse(imc: Double): Int {
        return when {
            imc < 15.0 -> R.string.imc_severely_low_weight
            imc < 16.0 -> R.string.imc_very_low_weight
            imc < 18.5 -> R.string.imc_low_weight
            imc < 25.0 -> R.string.normal
            imc < 30.0 -> R.string.imc_high_weight
            imc < 35.0 -> R.string.imc_so_high_weight
            imc < 40.0 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
        }

    }

    private fun calculateImc(weight: Int, height: Int): Double {
        return weight / ((height / 100.0) * (height / 100.0))
    }

    private fun validate(): Boolean {
        return (edtHeight.text.toString().isNotEmpty()
                && edtWeight.text.toString().isNotEmpty()
                && !edtHeight.text.toString().startsWith("0")
                && !edtWeight.text.toString().startsWith("0")
                )
    }
}