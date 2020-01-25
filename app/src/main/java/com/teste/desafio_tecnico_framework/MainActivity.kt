package com.teste.desafio_tecnico_framework

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.teste.desafio_tecnico_framework.framework.WebService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Passa a url do servidor e que já cria a instância do retrofit...
        WebService.baseURL = "https://jsonplaceholder.typicode.com"

        //Já começa abrindo o fragment de posts dentro da activity...
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragment_container,
                PostsFragment()
            )
            .commit()

        //Clique no item (ícone) do Bottom navigation bar
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null

            when (item.itemId) {
                R.id.action_posts -> {
                    fragment =
                        PostsFragment()
                }
                R.id.action_albums -> {
                    fragment =
                        AlbumsFragment()
                }
                R.id.action_todos -> {
                    fragment =
                        TodosFragment()
                }
            }

            //Abre o fragment de acordo com o item escolhido pelo usupario...
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment!!)
                .addToBackStack(null)
                .commit()

            return@setOnNavigationItemSelectedListener true
        }
    }
}
