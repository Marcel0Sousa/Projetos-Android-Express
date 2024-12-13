package com.marcelo.sousa.netflixremake.util

import com.marcelo.sousa.netflixremake.model.Category

interface Callback {
    fun onResult(categories: List<Category>)
    fun onFailure(message: String)
    fun onPreExecute()
}