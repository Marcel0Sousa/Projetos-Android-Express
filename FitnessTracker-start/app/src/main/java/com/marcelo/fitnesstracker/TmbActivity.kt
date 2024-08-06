package com.marcelo.fitnesstracker

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.marcelo.fitnesstracker.model.Calc

class TmbActivity : AppCompatActivity() {

    private lateinit var lifestyle: AutoCompleteTextView

    private lateinit var edtWeight: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtAge: EditText
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmb)

        edtWeight = findViewById(R.id.edt_tmb_weight)
        edtHeight = findViewById(R.id.edt_tmb_height)
        edtAge = findViewById(R.id.edt_tmb_age)
        btnSend = findViewById(R.id.btn_tmb_send)

        // Customiza a ToolBar
        val actionBar: ActionBar
        actionBar = supportActionBar!!
        val colorDrawable = ColorDrawable(getColor(R.color.color_tmb))
        actionBar.setBackgroundDrawable(colorDrawable)

        // Apontando ao layout do AutoComplete da caixa de seleção
        lifestyle = findViewById(R.id.auto_lifestyle)
        val items = resources.getStringArray(R.array.tmb_lifestyle)
        lifestyle.setText(items.first())
        val adapter = ArrayAdapter(this@TmbActivity, android.R.layout.simple_list_item_1, items)
        lifestyle.setAdapter(adapter)

        btnSend.setOnClickListener {
            if (!validate()) {
                Toast.makeText(this, R.string.fields_messages, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val weight = edtWeight.text.toString().toInt()
            val height = edtHeight.text.toString().toInt()
            val age = edtAge.text.toString().toInt()

            val result = calculateTmb(weight, height, age)
            val response = tmbRequest(result)

            AlertDialog.Builder(this)
                .setMessage(getString(R.string.tmb_response, response))
                .setPositiveButton(android.R.string.ok) { dialog, which ->
                    Toast.makeText(this, "Operação cancelada!", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton(R.string.save) { dialog, which ->
                    // Salvando os dados em uma thread para não travar a UI
                    Thread {
                        // Instancia do Room para inserir os dados no banco
                        val app = application as App
                        val dao = app.db.calcDao()
                        dao.insert(Calc(type = "tmb", response = response))

                        // Abre a ListCalcActivity
                        runOnUiThread {
                            finish()
                            openListActivity()
                        }
                    }.start()

                }
                .create()
                .show()

            // Ocultar teclado
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }

    }

    // Apontando ao layout do Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    // Seleciona o item da lista do Menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {
                finish()
                openListActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openListActivity() {
        val intent = Intent(this, ListCalcActivity::class.java)
        intent.putExtra("type", "tmb")
        startActivity(intent)
    }

    private fun tmbRequest(tmb: Double): Double {

        // Calcula o TMB conforme o estilo de vida
        val items = resources.getStringArray(R.array.tmb_lifestyle)
        return when {
            lifestyle.text.toString() == items[0] -> tmb * 1.2
            lifestyle.text.toString() == items[1] -> tmb * 1.375
            lifestyle.text.toString() == items[2] -> tmb * 1.55
            lifestyle.text.toString() == items[3] -> tmb * 1.725
            lifestyle.text.toString() == items[4] -> tmb * 1.9
            else -> return 0.0
        }

    }

    // Calcula o valor do TMB
    private fun calculateTmb(weight: Int, height: Int, age: Int): Double {
        return 66 + (13.8 * weight) + (5 * height) - (6.8 * age)
    }

    // Validação do formulário
    private fun validate(): Boolean {
        return (edtWeight.text.toString().isNotEmpty()
                && edtHeight.text.toString().isNotEmpty()
                && edtAge.text.toString().isNotEmpty()
                && !edtWeight.text.toString().startsWith("0")
                && !edtHeight.text.toString().startsWith("0")
                && !edtAge.text.toString().startsWith("0"))
    }
}