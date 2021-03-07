package com.developer.desafioinfo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developer.desafioinfo.data.entities.Noticia
import com.developer.desafioinfo.data.repository.NoticiaRepository
import com.developer.desafioinfo.utils.Resource

class NoticiasViewModel @ViewModelInject constructor(
    private val repository: NoticiaRepository
) : ViewModel() {
     lateinit var noticias : LiveData<Resource<List<Noticia>>>

    fun getAllNoticias(){
         noticias = repository.getNoticias()



    }
}