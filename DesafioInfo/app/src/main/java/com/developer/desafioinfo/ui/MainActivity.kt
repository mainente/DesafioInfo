package com.developer.desafioinfo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developer.desafioinfo.R
import com.developer.desafioinfo.ui.ui.noticias.NoticiasFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NoticiasFragment.newInstance())
                .commitNow()
        }
    }
}