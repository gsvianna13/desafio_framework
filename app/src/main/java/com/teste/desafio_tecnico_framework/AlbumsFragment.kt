package com.teste.desafio_tecnico_framework

import android.widget.TextView
import com.teste.desafio_tecnico_framework.framework.API
import com.teste.desafio_tecnico_framework.framework.FragmentListView
import com.teste.desafio_tecnico_framework.framework.WebService

class AlbumsFragment : FragmentListView<API.Album>() {

    private val webService by lazy {
        WebService.create(API::class.java)
    }

    var list: List<API.Album>? = null

    override suspend fun loadList(): List<API.Album> {
        if (list == null) {

            list = webService.getAlbums()

            return list!!
        }

        return list!!

    }

    override fun showItem(view: TextView, item: API.Album) {
        view.textAsHtml = """
            <b>userId: </b>${item.userId}<br/>
            <b>id: </b>${item.id}<br/>
            <b>title: </b>${item.title}<br/>
        """.trimIndent()
    }
}
