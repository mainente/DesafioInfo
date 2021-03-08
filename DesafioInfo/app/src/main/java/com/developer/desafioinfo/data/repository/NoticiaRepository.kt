package com.developer.desafioinfo.data.repository

import com.developer.desafioinfo.data.api.NoticiasRemoteDataSource
import com.developer.desafioinfo.data.local.NoticiaDao
import com.developer.desafioinfo.utils.performGetOperation

import javax.inject.Inject

class NoticiaRepository @Inject constructor(
    private val remoteDataSource: NoticiasRemoteDataSource,
    private val localDataSource: NoticiaDao
) {





    fun getNoticias(isUpdate: Boolean) = performGetOperation(isUpdate,
        databaseQuery = { localDataSource.getAllNoticias() },
        networkCall = { remoteDataSource.getNoticias() },
        saveCallResult = {
        val conteudo = (ArrayList(it));
            conteudo.forEach { it1 ->
                localDataSource.insertAl(it1.conteudos)

            }



        }
    )
}



