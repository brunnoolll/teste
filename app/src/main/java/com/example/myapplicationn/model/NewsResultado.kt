package com.example.myapplicationn.model

import java.io.Serializable

data class NewsResultado(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
):Serializable