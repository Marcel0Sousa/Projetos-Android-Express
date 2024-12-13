package com.marcelo.sousa.netflixremake.util

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.marcelo.sousa.netflixremake.model.Category
import com.marcelo.sousa.netflixremake.model.Movie
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class CategoryTask(private val callback: Callback) {

    private val handler = Handler(Looper.getMainLooper())

    fun execute(url: String) {
        callback.onPreExecute()
        // utilizando a UI-thread (1)
        val executor = Executors.newSingleThreadExecutor()

        executor.execute {

            var urlConnection: HttpsURLConnection? = null
            var buffer: BufferedInputStream? = null
            var inputStream: InputStream? = null

            try {
                // utilizando nova thread [processo paralelo]
                val requestURL = URL(url)
                urlConnection =
                    requestURL.openConnection() as HttpsURLConnection // abrir a conexão com servidor
                urlConnection.readTimeout = 2000 // tempo de leitura (2s)
                urlConnection.connectTimeout = 2000 // tempo de conexão (2)

                val statusCode: Int = urlConnection.responseCode
                if (statusCode > 400) {
                    throw IOException("Erro na comunicação com o servidor!")
                }

                /*
                // forma 1: simples e rápida
                val inputStream = urlConnection.inputStream // sequencia de bytes
                val jsonAsString = inputStream.bufferedReader().use { it.readText() }
                Log.i("Teste", jsonAsString)*/

                // forma 2:  bytes -> string

                inputStream = urlConnection.inputStream
                buffer = BufferedInputStream(inputStream)
                val jsonAsString = InputStreamAsString(buffer)

                Log.i("Teste", jsonAsString)
                // Converter JSON to Data Class
                val toCategories = toCategories(jsonAsString)

                handler.post {
                    // utilizando a UI-thread (2)
                    callback.onResult(toCategories)

                }

            } catch (e: IOException) {
                val message = e.message ?: "erro desconhecido"
                Log.e("Teste",message, e)
                callback.onFailure(message)
            } finally {
                urlConnection?.disconnect()
                inputStream?.close()
                buffer?.close()
            }
        }
    }

    private fun toCategories(josonAsString: String): List<Category> {
        val categories = mutableListOf<Category>()

        val jsonRoot = JSONObject(josonAsString)
        val jsonCategories = jsonRoot.getJSONArray("category")
        for (i in 0 until jsonCategories.length()) {
            val jsonCategory = jsonCategories.getJSONObject(i)
            val title = jsonCategory.getString("title")
            val jsonMovies = jsonCategory.getJSONArray("movie")

            val movies = mutableListOf<Movie>()
            for (j in 0 until jsonMovies.length()) {
                val jsonMovie = jsonMovies.getJSONObject(j)
                val id = jsonMovie.getInt("id")
                val coverUrl = jsonMovie.getString("cover_url")

                movies.add(Movie(id, coverUrl))
            }

            categories.add(Category(title, movies))
        }

        return categories
    }

    private fun InputStreamAsString(stream: InputStream): String {
        val bytes = ByteArray(1024) // Armazenagem de espaço em mamória
        val byteArrayOutputStream = ByteArrayOutputStream()
        var read: Int
        while (true) {
            read = stream.read(bytes)
            if (read <= 0) {
                break
            }
            byteArrayOutputStream.write(bytes, 0, read)
        }
        return String(byteArrayOutputStream.toByteArray())
    }
}