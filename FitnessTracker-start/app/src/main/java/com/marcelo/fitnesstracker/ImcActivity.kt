package com.marcelo.fitnesstracker

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity


class ImcActivity : AppCompatActivity() {
    private lateinit var edtWeight: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        edtWeight = findViewById(R.id.edt_img_weight)
        edtHeight = findViewById(R.id.edt_imc_height)
        btnSend = findViewById(R.id.btn_imc_send)

        btnSend.setOnClickListener {
            if (!validate()) {
                Toast.makeText(this, R.string.fields_messages, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val weight = edtWeight.text.toString().toInt()
            val height = edtHeight.text.toString().toInt()
            Log.i("imc", "${calculateImc(weight, height)}")

            val imcResponseId = imcResponse(calculateImc(weight, height))

            Toast.makeText(this, imcResponseId, Toast.LENGTH_SHORT).show()
        }
    }

    @StringRes
    private fun imcResponse(imc: Double): Int {
        if (imc < 15.0) {
            return R.string.imc_severely_low_weight
        } else if (imc < 16.0) {
            return R.string.imc_very_low_weight
        } else if (imc < 18.5) {
            return R.string.imc_low_weight
        } else if (imc < 25.0) {
            return R.string.normal
        } else if (imc < 30.0) {
            return R.string.imc_high_weight
        } else if (imc < 35.0) {
            return R.string.imc_so_high_weight
        } else if (imc < 40.0) {
            return R.string.imc_severely_high_weight
        } else {
            return R.string.imc_extreme_weight
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