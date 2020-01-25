package com.teste.desafio_tecnico_framework

import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.teste.desafio_tecnico_framework.framework.API
import com.teste.desafio_tecnico_framework.framework.FragmentListView
import com.teste.desafio_tecnico_framework.framework.WebService

class PostsFragment : FragmentListView<API.Post>() {

    private val webService by lazy {
        WebService.create(API::class.java)
    }

    var list: List<API.Post>? = null

    override suspend fun loadList(): List<API.Post> {
        if (list == null) {

            list = webService.getPosts()

            return list!!
        }

        return list!!

    }

    override fun showItem(view: TextView, item: API.Post) {
        view.textAsHtml = """
            <b>userId: </b>${item.userId}<br/>
            <b>id: </b>${item.id}<br/>
            <b>title: </b>${item.title}<br/>
            <b>body: </b>${item.body}<br/>
        """.trimIndent()
    }
}
