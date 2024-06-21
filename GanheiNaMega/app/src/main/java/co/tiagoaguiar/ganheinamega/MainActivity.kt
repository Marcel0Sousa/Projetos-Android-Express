package co.tiagoaguiar.ganheinamega

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import co.tiagoaguiar.ganheinamega.util.snackBar
import com.google.android.material.snackbar.Snackbar
import java.util.Random


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNumber: EditText = findViewById(R.id.edt_number)
        val tvResult: TextView = findViewById(R.id.txt_result)
        val btnGenerate: Button = findViewById(R.id.btn_generate)

        btnGenerate.setOnClickListener {
            val hideKeyboard = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            hideKeyboard.hideSoftInputFromWindow(btnGenerate.windowToken, 0)

            val textNumber = edtNumber.text.toString()
            numberGenerator(textNumber, tvResult)

        }

    }

    private fun numberGenerator(textNumber: String, txtResult: TextView) {

        // validar campo vazio
        if (textNumber.isEmpty()) {

            snackBar(txtResult, getString(R.string.message), Snackbar.LENGTH_LONG)
            return
        }

        val number = textNumber.toInt()
        if (number < 6 || number > 15) {

            snackBar(txtResult, getString(R.string.message), Snackbar.LENGTH_LONG)
            return
        }

        val listNumbers = mutableSetOf<Int>()
        val random = Random()

        while (true) {

            val numberRandom = random.nextInt(60) // 0...59
            listNumbers.add(numberRandom + 1)

            if (listNumbers.size == number) {
                break
            }
        }

        txtResult.text = listNumbers.joinToString(" - ")

    }
}