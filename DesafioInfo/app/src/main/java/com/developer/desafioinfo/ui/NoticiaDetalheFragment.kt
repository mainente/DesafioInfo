package com.developer.desafioinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.developer.desafioinfo.R
import com.developer.desafioinfo.data.entities.Noticia
import com.developer.desafioinfo.databinding.DetailsBinding
import com.developer.desafioinfo.utils.autoCleared
import com.google.gson.Gson
import com.squareup.picasso.Picasso

import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class NoticiaDetalheFragment : Fragment() {

    private var binding: DetailsBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var noticia: Noticia = Gson().fromJson(arguments?.getString("noticia"), Noticia::class.java)
            binding.noticia = noticia
        noticia.autores.let {
            if (it != null) {
                if (it.size>0){
                    binding.autor.text = "Por "+ it
                }
            }
        }

        var url: String =  ""
        if (noticia.imagens!=null && noticia.imagens!!.size>0){
            url = noticia.imagens?.get(0)?.url.toString()
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_erro)
                .into(binding.imvcapa)
        }
        noticia.secao?.nome?.let {
            binding.textViewToolbar.text = it
        }
        binding.textViewDataHora.text =   formattedDate(noticia.publicadoEm as String)




    }
    fun formattedDate(date: String): String{
        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        return  formatter.format(parser.parse(date))
    }



}
