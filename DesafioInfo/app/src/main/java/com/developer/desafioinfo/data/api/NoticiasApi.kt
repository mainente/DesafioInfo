package com.developer.desafioinfo.data.api

import com.developer.desafioinfo.data.entities.NoticiasResponse
import retrofit2.Response
import retrofit2.http.GET

interface NoticiasApi {
    @GET("capa.json")
    suspend fun getAllNoticias(): Response<List<NoticiasResponse>>
}