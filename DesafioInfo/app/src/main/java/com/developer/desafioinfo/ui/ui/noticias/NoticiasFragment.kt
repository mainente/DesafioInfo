package com.developer.desafioinfo.ui.ui.noticias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.developer.desafioinfo.R
import com.developer.desafioinfo.data.entities.Noticia
import com.developer.desafioinfo.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticiasFragment : Fragment() {

    companion object {
        fun newInstance() = NoticiasFragment()
    }

    private val viewModel: NoticiasViewModel by viewModels()
    private  var taste: List<Noticia>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.noticias_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
        // TODO: Use the ViewModel
    }

    private fun setupObservers() {
        viewModel.noticias.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                   taste = it.data
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

            }
        })
    }

}