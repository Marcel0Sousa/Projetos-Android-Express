package com.marcelo.sousa.netflixremake.util

import android.util.Log
import java.io.IOException
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class CategoryTask {

    fun execute(url:String) {
        // utilizando a UI-thread (1)
        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
            try {
                // utilizando nova thread [processo paralelo]
                val requestURL = URL(url)
                val urlConnection = requestURL.openConnection() as HttpsURLConnection // abrir a conexão com servidor
                urlConnection.readTimeout = 2000 // tempo de leitura (2s)
                urlConnection.connectTimeout = 2000 // tempo de conexão (2)

                val statusCode: Int = urlConnection.responseCode
                if (statusCode > 400) {
                    throw IOException("Erro na comunicação com o servidor!")
                }

                val inputStream = urlConnection.inputStream // sequencia de bytes
                val jsonAsString = inputStream.bufferedReader().use { it.readText() }
                Log.i("Teste", jsonAsString)

            } catch (e: IOException) {
                Log.e("Teste", e.message ?: "erro desconhecido")
            }

        }
    }
}