package com.developer.desafioinfo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.developer.desafioinfo.data.entities.Noticia
import com.developer.desafioinfo.data.repository.NoticiaRepository
import com.developer.desafioinfo.utils.Resource

class NoticiasViewModel @ViewModelInject constructor(
    private val repository: NoticiaRepository
) : ViewModel() {

    private val _id = MutableLiveData<Boolean>()

    private val _character = _id.switchMap { id ->
        repository.getNoticias(id)
    }
    var noticias : LiveData<Resource<List<Noticia>>> = _character

    fun getAllNoticias(isUpdate: Boolean){
        _id.value = isUpdate
    }
}