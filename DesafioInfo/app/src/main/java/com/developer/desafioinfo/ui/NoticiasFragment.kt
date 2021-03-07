package com.developer.desafioinfo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.desafioinfo.R
import com.developer.desafioinfo.data.entities.Noticia
import com.developer.desafioinfo.utils.Resource
import com.developer.desafioinfo.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import com.developer.desafioinfo.databinding.NoticiasFragmentBinding
import com.developer.desafioinfo.ui.noticia.adapter.MainAdapter
import com.developer.desafioinfo.ui.noticia.adapter.OnItemClickListener
import com.squareup.picasso.Picasso

@AndroidEntryPoint
class NoticiasFragment : Fragment(), OnItemClickListener {



    companion object {
        fun newInstance() = NoticiasFragment()
    }
    private var binding: NoticiasFragmentBinding by autoCleared()
    private val viewModel: NoticiasViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NoticiasFragmentBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        binding.swipeContainer.setOnRefreshListener {
            viewModel.getAllNoticias()
        }
        viewModel.getAllNoticias()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.noticias.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.swipeContainer.isRefreshing = false

                    if (!it.data.isNullOrEmpty()){
                        binding.view.visibility = View.VISIBLE
                        var princNoticia = it.data.filter { it1 -> it1.id == 21030945 }
                        loadBanner(princNoticia[0])
                        binding.recyclerView.apply {
                            setHasFixedSize(true)
                            adapter = MainAdapter(it.data,  this@NoticiasFragment)
                            layoutManager = LinearLayoutManager(requireContext())
                            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
                        }                    }

                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()


                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE

            }
        })
    }

    override fun onItemClicked(gson: String) {
    }

    private fun loadBanner(conteudo: Noticia) {
        binding.textViewTitulo.text = conteudo.titulo
        binding.textViewSecaoNome.text = conteudo.secao?.nome
        var url: String?
        if (conteudo.imagens!=null && conteudo.imagens!!.size>0){
            url = conteudo.imagens?.get(0)?.url.toString()
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_erro)
                .into(binding.imageView)
        }
    }

}