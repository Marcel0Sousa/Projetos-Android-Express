package co.tiagoaguiar.ganheinamega

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import co.tiagoaguiar.ganheinamega.databinding.ActivityMainBinding
import co.tiagoaguiar.ganheinamega.util.snackBar
import com.google.android.material.snackbar.Snackbar
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // persist data with SharedPreferences
    private lateinit var sharePreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // instance sharedPreferences
        sharePreferences = getSharedPreferences("data", Context.MODE_PRIVATE)

        // recover data persisting using SharedPreferences
        val result = sharePreferences.getString("result", null)
        if (result != null) {
            binding.tvResult.text = getString(R.string.str_last_bet, result)
        }

        binding.btnGenerate.setOnClickListener {

            // hide keyboard
            val hideKeyboard = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            hideKeyboard.hideSoftInputFromWindow(binding.btnGenerate.windowToken, 0)

            val textNumber = binding.edtNumber.text.toString()
            numberGenerator(textNumber, binding.tvResult)

        }

    }

    private fun numberGenerator(textNumber: String, txtResult: TextView) {

        // validade empty field
        if (textNumber.isEmpty()) {

            snackBar(txtResult, getString(R.string.str_message), Snackbar.LENGTH_LONG)
            return
        }

        // validade number range
        val number = textNumber.toInt()
        if (number < 6 || number > 15) {

            snackBar(txtResult, getString(R.string.str_message), Snackbar.LENGTH_LONG)
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

        txtResult.text = listNumbers.sorted().joinToString(" - ")

        // save data persisting using SharedPreferences
        val editor = sharePreferences.edit()
        editor.putString("result", txtResult.text.toString())
        editor.apply() // assinchrono method

    }
}