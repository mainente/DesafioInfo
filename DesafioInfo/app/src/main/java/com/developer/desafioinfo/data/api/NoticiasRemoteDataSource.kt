package com.developer.desafioinfo.data.api

import javax.inject.Inject

class NoticiasRemoteDataSource @Inject constructor(
    private val noticiaService: NoticiasApi
): BaseDataSource() {

    suspend fun getNoticias() = getResult { noticiaService.getAllNoticias() }
}