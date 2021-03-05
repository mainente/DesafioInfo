package com.developer.desafioinfo.ui.ui.noticias

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.developer.desafioinfo.data.repository.NoticiaRepository

class NoticiasViewModel @ViewModelInject constructor(
    private val repository: NoticiaRepository
) : ViewModel() {

    val noticias = repository.getNoticias()


    // TODO: Implement the ViewModel
}