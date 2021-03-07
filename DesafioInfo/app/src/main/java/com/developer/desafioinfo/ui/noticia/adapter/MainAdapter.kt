package com.developer.desafioinfo.ui.noticia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.desafioinfo.R
import com.developer.desafioinfo.data.entities.Noticia
import com.developer.desafioinfo.databinding.AdapterNoticiaListItemBinding

import com.google.gson.Gson
import com.squareup.picasso.Picasso

class MainAdapter(private val noticias: List<Noticia>, onItemClickListener : OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val onItemClickListener : OnItemClickListener = onItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_noticia_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MainViewHolder).binding
        val noticia = noticias[position]
        load(binding, noticia)
        binding.conteudo = noticia

        holder.itemView.setOnClickListener { view ->
            onItemClickListener.onItemClicked(Gson().toJson(noticia))
        }
    }

    private fun load(binding: AdapterNoticiaListItemBinding, noticia: Noticia) {
        binding.titulo.text = noticia.titulo
        var url: String?
        if (noticia.imagens!=null && noticia.imagens!!.size>0){
            url = noticia.imagens?.get(0)?.url.toString()
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_erro)
                .into(binding.image)
        }
    }

    override fun getItemCount() = noticias.size

}

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding: AdapterNoticiaListItemBinding = AdapterNoticiaListItemBinding.bind(view)
}

interface OnItemClickListener{
    fun onItemClicked(gson: String)
}