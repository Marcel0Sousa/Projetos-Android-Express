package com.marcelo.fitnesstracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
        }
    }

    private fun validate(): Boolean {
        return (edtHeight.text.toString().isNotEmpty()
                && edtWeight.text.toString().isNotEmpty()
                && !edtHeight.text.toString().startsWith("0")
                && !edtWeight.text.toString().startsWith("0")
                )
    }
}