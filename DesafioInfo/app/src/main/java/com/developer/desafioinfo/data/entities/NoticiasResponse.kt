package com.developer.desafioinfo.data.entities


data class NoticiasResponse(
    val conteudos: List<Noticia> = listOf(),

    val produto: String
)

