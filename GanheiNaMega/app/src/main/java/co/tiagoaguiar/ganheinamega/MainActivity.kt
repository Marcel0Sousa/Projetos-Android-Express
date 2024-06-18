package co.tiagoaguiar.ganheinamega

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import co.tiagoaguiar.ganheinamega.util.toast
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.edt_number)
        val textResult: TextView = findViewById(R.id.txt_result)
        val btnGenerate: Button = findViewById(R.id.btn_generate)

        btnGenerate.setOnClickListener {
            val text = editText.text.toString()

            numberGenerator(text, textResult)
        }

    }

    private fun numberGenerator(text: String, txtResult: TextView) {
        // validar campo vazio
        if (text.isNotEmpty()) {

            val quantidade = text.toInt()

            if (quantidade >= 6 && quantidade <= 15) {

                val numbers = mutableSetOf<Int>()
                val random = Random()

                while (true) {

                    val number = random.nextInt(60) // 0...59
                    numbers.add(number + 1)

                    if (numbers.size == quantidade) {
                        break
                    }
                }

                txtResult.text = numbers.joinToString(" - ")

            } else {
                toast(this, "Informe um numero entre 6 e 15", Toast.LENGTH_LONG)
            }

        } else {
            toast(this, "Informe um numero entre 6 e 15", Toast.LENGTH_LONG)

        }

    }
}